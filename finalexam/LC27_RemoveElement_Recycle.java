// 檔名：LC27_RemoveElement_Recycle.java
// 讀取：n val；接著 n 個整數
// 輸出：移除所有等於 val 的元素後，保留的新序列（空白分隔）
//
// 時間 O(n)，空間 O(1)

import java.io.*;
import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int val = fs.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = fs.nextInt();

        int write = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != val) arr[write++] = arr[i];
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < write; i++) {
            if (i > 0) out.append(' ');
            out.append(arr[i]);
        }
        System.out.println(out.toString());
    }

    // 簡易輸入工具
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        FastScanner(InputStream is) { br = new BufferedReader(new InputStreamReader(is)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}
