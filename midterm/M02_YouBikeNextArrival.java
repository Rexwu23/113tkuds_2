import java.io.*;

/**
 * M02_YouBikeNextArrival.java
 * 輸入：
 *   n
 *   n 行已排序時刻（HH:mm）
 *   query（HH:mm）
 * 輸出：
 *   下一班到站時間（HH:mm），若無則輸出 "No bike"
 */
public class M02_YouBikeNextArrival {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] t = new int[n]; // 轉成自 00:00 起的分鐘數
        for (int i = 0; i < n; i++) t[i] = parse(br.readLine().trim());

        int q = parse(br.readLine().trim());

        // 二分搜尋「第一個 >= q」的索引
        int lo = 0, hi = n; // [lo, hi)
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (t[mid] >= q) hi = mid;
            else lo = mid + 1;
        }

        if (lo == n) {
            System.out.println("No bike");
        } else {
            System.out.println(format(t[lo]));
        }
    }

    // "HH:mm" -> 分鐘
    private static int parse(String s) {
        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3, 5));
        return h * 60 + m;
    }

    // 分鐘 -> "HH:mm"
    private static String format(int mins) {
        int h = mins / 60, m = mins % 60;
        return String.format("%02d:%02d", h, m);
    }
}

/*
複雜度：
- 時間：O(log n)（二分搜尋）
- 空間：O(1) 額外空間（不含輸入陣列）
*/