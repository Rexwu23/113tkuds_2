import java.io.*;
import java.util.*;

public class LC01_TwoSum_THSR0Holiday {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 讀第一行：n 與 target
        st = new StringTokenizer(nextNonEmptyLine(br));
        int n = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());

        // 讀第二行：n 個數
        long[] a = new long[n];
        st = new StringTokenizer(nextNonEmptyLine(br));
        for (int i = 0; i < n; i++) a[i] = Long.parseLong(st.nextToken());

        // 兩數和：HashMap<值, 最早出現的索引>
        Map<Long, Integer> seen = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long need = target - a[i];
            Integer j = seen.get(need);
            if (j != null) {                     // 找到一組
                System.out.println((j + 1) + " " + (i + 1));
                return;
            }
            // 只保留最早索引，確保輸出 i<j
            seen.putIfAbsent(a[i], i);
        }
        System.out.println("-1 -1");
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