class Solution {
    public int romanToInt(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = val(s.charAt(i));
            // 看下一個是否更大（減法情況）
            if (i + 1 < n && cur < val(s.charAt(i + 1))) {
                ans -= cur;
            } else {
                ans += cur;
            }
        }
        return ans;
    }

    private int val(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:  return 0; // 題目保證輸入有效，可視需要丟例外
        }
    }
}
