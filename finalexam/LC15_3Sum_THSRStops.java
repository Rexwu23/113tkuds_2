// 檔名：LC15_3Sum_THSRStops.java
// 讀取：n；接著 n 個整數
// 輸出：所有不重複的三元組 (a,b,c) 使得 a+b+c=0
//
// 時間 O(n^2)，空間 O(1) (輸出除外)

import java.io.*;
import java.util.*;

public class LC15_3Sum_THSRStops {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = fs.nextInt();

        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 跳過重複 i
            if (nums[i] > 0) break; // 已排序，後面不可能再有解

            int L = i + 1, R = n - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    L++;
                    R--;
                    while (L < R && nums[L] == nums[L - 1]) L++; // 跳過重複
                    while (L < R && nums[R] == nums[R + 1]) R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }

        // 輸出
        for (List<Integer> t : ans) {
            System.out.println(t.get(0) + " " + t.get(1) + " " + t.get(2));
        }
    }

    // 簡易快速讀取
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        FastScanner(InputStream is) { br = new BufferedReader(new InputStreamReader(is)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}
