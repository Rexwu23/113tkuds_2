import java.io.*;
import java.util.*;

/**
 * M07_BinaryTreeLeftView.java
 *
 * 輸入：
 *   n
 *   v1 v2 ... vn   // 層序，-1 表 null
 *
 * 輸出：
 *   LeftView: a b c ...
 *
 * 範例：
 *   Input:
 *     7
 *     1 2 3 4 -1 -1 5
 *   Output:
 *     LeftView: 1 2 4
 */
public class M07_BinaryTreeLeftView {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Node root = buildTree(arr);
        List<Integer> leftView = getLeftView(root);

        StringBuilder out = new StringBuilder("LeftView:");
        for (int v : leftView) out.append(' ').append(v);
        System.out.println(out.toString());
    }

    // 建樹（由層序陣列）
    static Node buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node cur = q.poll();
            if (i < arr.length && arr[i] != -1) {
                cur.left = new Node(arr[i]);
                q.add(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                cur.right = new Node(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    // BFS：取每層第一個節點
    static List<Integer> getLeftView(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (i == 0) res.add(cur.val); // 最左側
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
        }
        return res;
    }
}

/*
複雜度：
- 建樹 O(n)，BFS O(n)
- 時間：O(n)
- 空間：O(n)（佇列與輸出）
*/