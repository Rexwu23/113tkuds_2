import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return ans;
        int n = s.length(), m = words.length, L = words[0].length();
        if (L == 0 || n < m * L) return ans;

        Map<String, Integer> need = new HashMap<>();
        for (String w : words) need.put(w, need.getOrDefault(w, 0) + 1);

        for (int offset = 0; offset < L; offset++) {
            int left = offset, count = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = offset; right + L <= n; right += L) {
                String w = s.substring(right, right + L);

                if (!need.containsKey(w)) {
                    // reset window
                    window.clear();
                    count = 0;
                    left = right + L;
                    continue;
                }

                window.put(w, window.getOrDefault(w, 0) + 1);
                count++;

                // shrink while w is over-used
                while (window.get(w) > need.get(w)) {
                    String lw = s.substring(left, left + L);
                    window.put(lw, window.get(lw) - 1);
                    left += L;
                    count--;
                }

                if (count == m) {
                    ans.add(left);
                    // move left by one word to continue searching
                    String lw = s.substring(left, left + L);
                    window.put(lw, window.get(lw) - 1);
                    left += L;
                    count--;
                }
            }
        }
        return ans;
    }
}
