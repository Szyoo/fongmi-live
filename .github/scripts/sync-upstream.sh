#!/usr/bin/env bash
# fongmi-live：把 live 分支 rebase 到上游 FongMi/TV 的最新代码上。
#
# 本分支删除了少量上游文件（插件加载器、P2P 提取器、闭源 aar）。上游之后若再修改
# 这些文件，rebase 会出现「我们删除 / 上游修改」的冲突——这类冲突一律保持删除并继续。
# 其余任何冲突都说明上游改动和我们的补丁有真实交叠，脚本会中止并以失败退出，
# 交给人工处理（CI 失败会触发 GitHub 的邮件通知）。
#
# 退出码：0 = 已是最新或 rebase 成功；1 = 需要人工处理。
# 输出：在 $GITHUB_OUTPUT 中写入 changed=true|false。
set -euo pipefail

UPSTREAM_URL="https://github.com/FongMi/TV.git"
UPSTREAM_BRANCH="fongmi"
LIVE_BRANCH="live"

out() { if [ -n "${GITHUB_OUTPUT:-}" ]; then echo "$1" >> "$GITHUB_OUTPUT"; fi; }
in_rebase() { [ -d "$(git rev-parse --git-path rebase-merge)" ] || [ -d "$(git rev-parse --git-path rebase-apply)" ]; }
unmerged() { git diff --name-only --diff-filter=U; }

git remote get-url upstream >/dev/null 2>&1 || git remote add upstream "$UPSTREAM_URL"
git fetch --quiet upstream "$UPSTREAM_BRANCH"
git checkout --quiet "$LIVE_BRANCH"

if git merge-base --is-ancestor "upstream/$UPSTREAM_BRANCH" HEAD; then
    echo "live 已包含上游最新提交，无需同步。"
    out "changed=false"
    exit 0
fi

echo "上游新提交："
git log --oneline "HEAD..upstream/$UPSTREAM_BRANCH" | head -30

git rebase "upstream/$UPSTREAM_BRANCH" >/dev/null 2>&1 || true

for _ in $(seq 1 500); do
    in_rebase || break

    # 1) 「我们删除 / 上游修改」：保持删除
    while IFS= read -r path; do
        [ -z "$path" ] && continue
        st=$(git status --porcelain -- "$path" | cut -c1-2)
        case "$st" in
            UD|DU) echo "  保持删除：$path"; git rm --quiet -- "$path" ;;
        esac
    done <<< "$(unmerged)"

    # 2) 还剩冲突 → 真实交叠，交给人工
    if [ -n "$(unmerged)" ]; then
        echo "::error::rebase 出现需要人工处理的冲突："
        git status --porcelain | grep -E '^(UU|AA|DD|AU|UA)' || true
        echo "冲突发生在补丁：$(git log -1 --format='%h %s' REBASE_HEAD 2>/dev/null || true)"
        git rebase --abort
        exit 1
    fi

    # 3) 继续；若该补丁在解决后已为空（上游已等价合入），跳过它
    if ! GIT_EDITOR=true git rebase --continue >/dev/null 2>&1; then
        if in_rebase && [ -z "$(unmerged)" ] && git diff --cached --quiet; then
            git rebase --skip >/dev/null 2>&1 || true
        fi
    fi
done

if in_rebase; then
    echo "::error::rebase 未能在预期步数内完成"
    git rebase --abort
    exit 1
fi

echo "rebase 完成：$(git log -1 --format='%h %s')"
out "changed=true"
