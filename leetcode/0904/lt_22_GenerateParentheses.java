import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        backtrack(n, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void backtrack(int n, int open, int close, StringBuilder path, List<String> res) {
        if (path.length() == 2 * n) {
            res.add(path.toString());
            return;
        }
        if (open < n) {                 // 還能放左括號
            path.append('(');
            backtrack(n, open + 1, close, path, res);
            path.deleteCharAt(path.length() - 1);
        }
        if (close < open) {             // 右括號數量不可超過左括號
            path.append(')');
            backtrack(n, open, close + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
