import java.io.*;
import java.util.*;

/**
 * M08_BSTRangedSum.java
 *
 * 輸入：
 *   n
 *   v1 v2 ... vn    // 層序，-1 表 null
 *   L R
 * 輸出：
 *   Sum: s
 */
public class M08_BSTRangedSum {
    static class Node {
        int val;
        Node left, right;
        Node(int v){ val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        Node root = buildTree(arr);
        long sum = rangeSumBST(root, L, R);
        System.out.println("Sum: " + sum);
    }

    // 由層序建樹
    static Node buildTree(int[] a) {
        if (a.length == 0 || a[0] == -1) return null;
        Node root = new Node(a[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < a.length) {
            Node cur = q.poll();
            if (i < a.length && a[i] != -1) {
                cur.left = new Node(a[i]);
                q.add(cur.left);
            }
            i++;
            if (i < a.length && a[i] != -1) {
                cur.right = new Node(a[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    // 以 BST 性質剪枝的 DFS
    static long rangeSumBST(Node root, int L, int R) {
        if (root == null) return 0L;
        if (root.val < L) return rangeSumBST(root.right, L, R);
        if (root.val > R) return rangeSumBST(root.left, L, R);
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }
}

/*
複雜度：
- 時間：平均 O(m)，m 為被造訪的節點數（剪枝後 ≤ n）
- 空間：O(h)，h 為樹高（遞迴堆疊）
*/