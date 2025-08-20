import java.io.*;
import java.util.*;

/**
 * M12_MergeKTimetables.java
 *
 * 輸入：
 *   K
 *   len1
 *   x11 x12 ...
 *   len2
 *   x21 x22 ...
 *   ...
 *   （時間以分鐘或 HH:mm；各表皆遞增）
 *
 * 輸出：
 *   merged list（遞增），同一行以空白分隔。
 */
public class M12_MergeKTimeTables {

    static class Node {
        int t;      // minutes from 00:00
        int li;     // which list
        int idx;    // index in that list
        Node(int t, int li, int idx){ this.t=t; this.li=li; this.idx=idx; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int K = Integer.parseInt(fs.nextToken());
        List<int[]> lists = new ArrayList<>(K);
        boolean outputHHmm = false;

        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(fs.nextToken());
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                String tok = fs.nextToken();
                if (tok.indexOf(':') >= 0) outputHHmm = true;
                arr[j] = parseTime(tok);
            }
            lists.add(arr);
        }

        // Min-heap：time 小者先；若同時刻，依來源序、來源內索引決定（為了穩定性）
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.t != b.t) return Integer.compare(a.t, b.t);
            if (a.li != b.li) return Integer.compare(a.li, b.li);
            return Integer.compare(a.idx, b.idx);
        });

        for (int i = 0; i < K; i++) {
            int[] arr = lists.get(i);
            if (arr.length > 0) pq.add(new Node(arr[0], i, 0));
        }

        StringBuilder out = new StringBuilder();
        boolean first = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first) out.append(' ');
            first = false;
            out.append(outputHHmm ? formatHHmm(cur.t) : cur.t);

            int[] arr = lists.get(cur.li);
            int next = cur.idx + 1;
            if (next < arr.length) pq.add(new Node(arr[next], cur.li, next));
        }

        System.out.println(out.toString());
    }

    // ---- helpers ----
    private static int parseTime(String s) {
        int p = s.indexOf(':');
        if (p < 0) return Integer.parseInt(s); // minutes
        int h = Integer.parseInt(s.substring(0, p));
        int m = Integer.parseInt(s.substring(p + 1));
        return h * 60 + m;
    }

    private static String formatHHmm(int minutes) {
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    /** 簡易 token reader（可跨行） */
    static class FastScanner {
        BufferedReader br; StringTokenizer st;
        FastScanner(InputStream is){ br = new BufferedReader(new InputStreamReader(is)); }
        String nextToken() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                while (line != null && line.trim().isEmpty()) line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
    }
}

/*
複雜度：
- 令總元素數為 T、清單數 K（K ≤ 5）。
- 時間：O(T log K)，每取出/插入一次堆為 O(log K)。
- 空間：O(K) 供最小堆；讀入資料 O(T) 由輸入儲存。
*/