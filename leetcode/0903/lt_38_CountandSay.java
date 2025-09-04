class Solution {
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder t = new StringBuilder();
            for (int j = 0; j < s.length(); ) {
                char ch = s.charAt(j);
                int k = j;
                while (k < s.length() && s.charAt(k) == ch) k++;
                t.append(k - j).append(ch);
                j = k;
            }
            s = t.toString();
        }
        return s;
    }
}
