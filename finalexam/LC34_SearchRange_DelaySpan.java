// 檔名：LC34_SearchRange_DelaySpan.java
// 讀取：第一行 n target；第二行 n 個「已排序（非遞減）」整數
// 輸出：target 的「首個」與「最後一個」索引（0-based）。若不存在輸出 "-1 -1"
// 解法：兩次二分：left = lower_bound(target)，right = upper_bound(target)-1
// 時間 O(log n)，空間 O(1)

import java.io.*;
import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(nextNonEmptyLine(br));
        int n = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());

        long[] a = new long[n];
        if (n > 0) {
            st = new StringTokenizer(nextNonEmptyLine(br));
            for (int i = 0; i < n; i++) a[i] = Long.parseLong(st.nextToken());
        }

        int l = lowerBound(a, target);
        if (l == n || a[l] != target) { // 不存在
            System.out.println("-1 -1");
            return;
        }
        int r = upperBound(a, target) - 1;
        System.out.println(l + " " + r);
    }

    // 回傳第一個 >= x 的位置
    private static int lowerBound(long[] a, long x) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] >= x) r = m;
            else l = m + 1;
        }
        return l;
    }

    // 回傳第一個 > x 的位置
    private static int upperBound(long[] a, long x) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] > x) r = m;
            else l = m + 1;
        }
        return l;
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
