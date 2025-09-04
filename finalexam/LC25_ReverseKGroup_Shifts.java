// 檔名：LC25_ReverseKGroup_Shifts.java
// 讀取：k；接著一行整數序列（以空白分隔，可為空）
// 輸出：每 k 個元素為一組反轉後的序列，若最後不足 k 則保持不變
//
// 時間 O(n)，空間 O(1)（就地反轉）

import java.io.*;
import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = nextNonEmptyLine(br);
        if (line == null) { System.out.println(""); return; }
        int k = Integer.parseInt(line.trim());

        line = br.readLine();
        if (line == null || (line = line.trim()).isEmpty()) {
            System.out.println(""); return;
        }
        StringTokenizer st = new StringTokenizer(line);
        ArrayList<Integer> list = new ArrayList<>();
        while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));

        int n = list.size();
        if (k > 1) {
            for (int i = 0; i + k <= n; i += k) {
                reverse(list, i, i + k - 1);
            }
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(list.get(i));
        }
        System.out.println(out.toString());
    }

    private static void reverse(ArrayList<Integer> arr, int L, int R) {
        while (L < R) {
            int tmp = arr.get(L);
            arr.set(L, arr.get(R));
            arr.set(R, tmp);
            L++; R--;
        }
    }

    private static String nextNonEmptyLine(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (!s.isEmpty()) return s;
        }
        return null;
    }
}
