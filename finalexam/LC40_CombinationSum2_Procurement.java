// 檔名：LC40_CombinationSum2_Procurement.java
// 讀取：第一行 target；第二行 n 個整數（候選物資）
// 輸出：每行一個升序組合，和為 target
//
// 解法：DFS + 回溯（每個元素只能使用一次，需去重複）
// 時間：指數；空間：O(n)

import java.io.*;
import java.util.*;

public class LC40_CombinationSum2_Procurement {
    static List<List<Integer>> ans = new ArrayList<>();
    static int[] nums;
    static int target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(nextNonEmptyLine(br));
        StringTokenizer st = new StringTokenizer(nextNonEmptyLine(br));
        ArrayList<Integer> list = new ArrayList<>();
        while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
        nums = list.stream().mapToInt(i -> i).toArray();
        Arrays.sort(nums);

        dfs(0, target, new ArrayList<>());

        for (List<Integer> c : ans) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < c.size(); i++) {
                if (i > 0) sb.append(' ');
                sb.append(c.get(i));
            }
            System.out.println(sb.toString());
        }
    }

    private static void dfs(int start, int remain, List<Integer> path) {
        if (remain == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // 避免重複組合
            if (nums[i] > remain) break;
            path.add(nums[i]);
            dfs(i + 1, remain - nums[i], path); // 每個元素僅用一次
            path.remove(path.size() - 1);
        }
    }

    private static String nextNonEmptyLine(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (!s.isEmpty()) return s;
        }
        return "";
    }
}