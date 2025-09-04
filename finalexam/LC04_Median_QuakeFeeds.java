// 檔名：LC04_Median_QuakeFeeds.java
// 讀取：第一行 n m；第二行 n 個已排序浮點數；第三行 m 個已排序浮點數
// 輸出：兩個已排序序列合併後的中位數（double）

import java.io.*;
import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 第一行：n, m
        st = new StringTokenizer(nextNonEmptyLine(br));
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        double[] A = new double[n];
        double[] B = new double[m];

        if (n > 0) {
            st = new StringTokenizer(nextNonEmptyLine(br));
            for (int i = 0; i < n; i++) A[i] = Double.parseDouble(st.nextToken());
        } else {
            // 若 n=0，仍要吃掉可能的空白行
            nextMaybeEmptyLine(br);
        }

        if (m > 0) {
            st = new StringTokenizer(nextNonEmptyLine(br));
            for (int i = 0; i < m; i++) B[i] = Double.parseDouble(st.nextToken());
        } else {
            nextMaybeEmptyLine(br);
        }

        System.out.println(findMedianSortedArrays(A, B));
    }

    // O(log(min(n,m))) 兩已排序陣列中位數
    private static double findMedianSortedArrays(double[] A, double[] B) {
        // 讓 A 是較短的
        if (A.length > B.length) return findMedianSortedArrays(B, A);
        int n = A.length, m = B.length;

        int leftCount = (n + m + 1) / 2; // 左半部元素個數
        int lo = 0, hi = n;

        while (lo <= hi) {
            int i = (lo + hi) >>> 1;         // A 的切分
            int j = leftCount - i;           // B 的切分

            double Aleft  = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft  = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                // 合法切分
                if (((n + m) & 1) == 1) { // 奇數
                    return Math.max(Aleft, Bleft);
                } else {                  // 偶數
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                hi = i - 1;  // A 左邊太大，往左縮
            } else {
                lo = i + 1;  // A 右邊太小，往右擴
            }
        }
        // 正常不會到這裡
        return 0.0;
    }

    private static String nextNonEmptyLine(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (!s.isEmpty()) return s;
        }
        return "";
    }
    private static void nextMaybeEmptyLine(BufferedReader br) throws IOException {
        br.mark(1);
        int c = br.read();
        if (c != -1) br.reset(); // 若下一行不是空就復位，之後再讀
    }
}
