public class lt_03_LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];          // ASCII
        java.util.Arrays.fill(last, -1);

        int left = 0, ans = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (last[c] >= left) {          // c 在當前視窗內重複
                left = last[c] + 1;         // 視窗左端右移
            }
            last[c] = right;                // 更新 c 的最新位置
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
