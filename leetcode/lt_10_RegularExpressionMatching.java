class lt_10_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // 空字串匹配形如 a*, a*b*, a*b*c* 的模式
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);

                if (pc != '*') {
                    // 當前字元直接匹配：字元相等或 p 為 '.'
                    if (matches(s.charAt(i - 1), pc)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // pc == '*', 看前一個模式字元
                    char prev = p.charAt(j - 2);

                    // 1) 用 0 次：丟掉 "prev*" 這兩個
                    dp[i][j] = dp[i][j - 2];

                    // 2) 用 ≥1 次：當 s[i-1] 能匹配 prev，吃掉一個 s 的字元
                    if (matches(s.charAt(i - 1), prev)) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    private boolean matches(char sc, char pc) {
        return pc == '.' || pc == sc;
    }
}
