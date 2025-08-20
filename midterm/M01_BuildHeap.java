import java.io.*;
import java.util.*;

/**
 * M01_BuildHeap.java
 * 讀入：
 *   type        // "max" 或 "min"
 *   n
 *   v1 v2 ... vn
 * 輸出：
 *   建堆後的陣列 (0-based)，以空白分隔
 */
public class M01_BuildHeap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String type = br.readLine().trim().toLowerCase();   // max 或 min
        int n = Integer.parseInt(br.readLine().trim());

        long[] a = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            a[i] = Long.parseLong(st.nextToken());
        }

        boolean isMax = type.equals("max");
        buildHeap(a, isMax);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(a[i]);
        }
        System.out.println(out.toString());
    }

    // 自底向上建堆：從最後一個非葉節點開始做 heapifyDown
    static void buildHeap(long[] a, boolean isMax) {
        for (int i = (a.length >>> 1) - 1; i >= 0; i--) {
            heapifyDown(a, i, a.length, isMax);
        }
    }

    // 將 idx 向下調整到正確位置（0-based）
    static void heapifyDown(long[] a, int idx, int size, boolean isMax) {
        while (true) {
            int left = (idx << 1) + 1;
            if (left >= size) break;
            int right = left + 1;

            int best = left;
            if (right < size && better(a[right], a[left], isMax)) best = right;

            if (better(a[best], a[idx], isMax)) {
                swap(a, best, idx);
                idx = best;
            } else break;
        }
    }

    // 比較器：對 max-heap 取較大，對 min-heap 取較小
    static boolean better(long x, long y, boolean isMax) {
        return isMax ? x > y : x < y;
    }

    static void swap(long[] a, int i, int j) {
        long t = a[i]; a[i] = a[j]; a[j] = t;
    }
}

/*
複雜度說明：
- 時間複雜度：O(n)。自底向上建堆對每層節點的下沉成本隨高度遞減，總成本 ≤ 2n。
- 空間複雜度：O(1) ；就地調整，僅使用少量暫存變數。
*/