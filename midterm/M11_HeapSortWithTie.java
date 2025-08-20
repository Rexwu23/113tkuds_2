import java.io.*;

/**
 * M11_HeapSortWithTie.java
 *
 * 輸入：
 *   n
 *   s1 s2 ... sn
 * 輸出（遞增）：
 *   sorted_s1 ... sorted_sn
 *
 * 規則：
 *   先依分數升序；若同分，以輸入時的索引升序決定先後。
 */
public class M11_HeapSortWithTie {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int[] score = new int[n];
        int[] idx   = new int[n];     // 儲存原始索引（0-based）
        for (int i = 0; i < n; i++) {
            score[i] = fs.nextInt();
            idx[i] = i;
        }

        heapSort(score, idx);         // 以 (score 升序, idx 升序) 排序

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(score[i]);
        }
        System.out.println(out.toString());
    }

    // ===== Heapsort（就地） =====
    // 以「比較結果 > 0」為 “更大”，建立 max-heap，從尾端回填。
    static void heapSort(int[] s, int[] idx) {
        int n = s.length;
        // 建立 max-heap
        for (int i = (n >>> 1) - 1; i >= 0; i--) {
            heapifyDown(s, idx, n, i);
        }
        // 反覆把最大者丟到尾端
        for (int end = n - 1; end > 0; end--) {
            swap(s, idx, 0, end);
            heapifyDown(s, idx, end, 0);
        }
    }

    // 將 i 位置向下調整（heap size = size）
    static void heapifyDown(int[] s, int[] idx, int size, int i) {
        while (true) {
            int l = (i << 1) + 1, r = l + 1, best = i;
            if (l < size && greater(s, idx, l, best)) best = l;
            if (r < size && greater(s, idx, r, best)) best = r;
            if (best == i) break;
            swap(s, idx, i, best);
            i = best;
        }
    }

    // “greater” 依最終排序的反向定義：
    // 我們要的最終順序是 (score 升序, idx 升序)；
    // 因此「較大」定義為 (score 高) 或 (同分且 idx 大)。
    static boolean greater(int[] s, int[] idx, int i, int j) {
        if (s[i] != s[j]) return s[i] > s[j];
        return idx[i] > idx[j];
    }

    static void swap(int[] s, int[] idx, int i, int j) {
        int ts = s[i]; s[i] = s[j]; s[j] = ts;
        int ti = idx[i]; idx[i] = idx[j]; idx[j] = ti;
    }

    // ------------- 快速輸入 -------------
    static class FastScanner {
       private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is){ in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, sign = 1, x = 0;
            do { c = read(); } while (c <= ' ');     // skip spaces
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') { x = x * 10 + (c - '0'); c = read(); }
            return x * sign;
        }
    }
}

/*
複雜度（n 筆分數）：
- 建堆 O(n)，每次抽最大 O(log n)，共 n-1 次 → O(n log n)
- 空間 O(1) ；就地排序（除了索引陣列）
*/