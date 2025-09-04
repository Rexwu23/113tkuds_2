// 檔名：LC26_RemoveDuplicates_Scores.java
// 讀取：n；接著 n 個已排序（非遞減）整數
// 輸出：刪除重複後的陣列（只保留一次），以空白分隔
//
// 時間 O(n)，空間 O(1)

import java.io.*;
import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        if (n <= 0) { 
            System.out.println(""); 
            return; 
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = fs.nextInt();

        int write = 1; // 第一個元素一定保留
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[write - 1]) {
                arr[write] = arr[i];
                write++;
            }
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < write; i++) {
            if (i > 0) out.append(' ');
            out.append(arr[i]);
        }
        System.out.println(out.toString());
    }

    // 簡單輸入工具
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
