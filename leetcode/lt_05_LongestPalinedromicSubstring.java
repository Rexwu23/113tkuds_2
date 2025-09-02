public class lt_05_LongestPalinedromicSubstring {
    // Time: O(n^2), Space: O(1)
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // 奇數長度的回文，以 i 為中心
            int len1 = expand(s, i, i);
            // 偶數長度的回文，以 i 和 i+1 為中心
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                // 新回文的左右邊界
                int half = (len - 1) / 2;
                start = i - half;
                end   = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // 回傳以 [L, R] 為中心能擴到的回文長度
    private int expand(String s, int L, int R) {
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--; R++;
        }
        return R - L - 1; // 擴過頭一格，長度需 -1 -1
    }
}
