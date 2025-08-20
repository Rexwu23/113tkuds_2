import java.io.*;
import java.util.*;

/**
 * M03_TopKConvenience.java
 *
 * 輸入：
 *   n K
 *   接著 n 行：name qty  （name 無空白、qty 為非負整數）
 * 輸出：
 *   最高到最低的前 K 名，每行 "name qty"
 *
 * 規則（為了決定性）：
 *   排序依「qty 由大到小；若 qty 相同，name 由小到大（字典序）」。
 */
public class M03_TopKConvenience {

    static class Item {
        String name;
        int qty;
        Item(String n, int q){ name=n; qty=q; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 最小堆：qty 小者在前；qty 相同時，name 字典序大的在前（視為更差）
        PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return Integer.compare(a.qty, b.qty); // 小在前
                return b.name.compareTo(a.name); // 大的在前（讓它先被淘汰）
            }
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int qty = Integer.parseInt(st.nextToken());
            Item cur = new Item(name, qty);

            if (pq.size() < K) {
                pq.add(cur);
            } else {
                Item worst = pq.peek();
                // 若 cur 比堆頂更好（qty 大；或 qty 相同且名字更小），就換掉
                if (qty > worst.qty || (qty == worst.qty && name.compareTo(worst.name) < 0)) {
                    pq.poll();
                    pq.add(cur);
                }
            }
        }

        // 取出並依最終輸出規則排序：qty 降序；若相同 name 升序
        List<Item> ans = new ArrayList<>(pq);
        ans.sort((a, b) -> {
            if (a.qty != b.qty) return Integer.compare(b.qty, a.qty);
            return a.name.compareTo(b.name);
        });

        StringBuilder out = new StringBuilder();
        for (Item it : ans) out.append(it.name).append(' ').append(it.qty).append('\n');
        System.out.print(out.toString());
    }
}

/*
複雜度：
- 時間：O(n log K)（每筆最多做一次堆操作，K ≤ 50）
- 空間：O(K)（僅保存前 K 名）
*/