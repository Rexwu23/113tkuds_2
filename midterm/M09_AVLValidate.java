import java.io.*;
import java.util.*;

/**
 * 檢查同時滿足 BST 與 AVL
 * 輸入：
 *   n
 *   v1 v2 ... vn    // 層序，-1 代表 null
 * 輸出：
 *   Valid / Invalid BST / Invalid AVL
 */
public class M09_AVLValidate {
    static class Node {
        int val; Node left, right;
        Node(int v){ val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

        Node root = buildTree(a);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }
        if (!isAVL(root)) {
            System.out.println("Invalid AVL");
            return;
        }
        System.out.println("Valid");
    }

    // 由層序建樹（-1 表 null）
    static Node buildTree(int[] arr){
        if (arr.length == 0 || arr[0] == -1) return null;
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(arr[0]);
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length){
            Node cur = q.poll();
            if (i < arr.length && arr[i] != -1){
                cur.left = new Node(arr[i]);
                q.add(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1){
                cur.right = new Node(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    // 檢查 BST：遞迴帶上下界（嚴格不等）
    static boolean isBST(Node x, long min, long max){
        if (x == null) return true;
        if (!(min < x.val && x.val < max)) return false;
        return isBST(x.left, min, x.val) && isBST(x.right, x.val, max);
    }

    // 檢查 AVL：回傳高度；若不平衡回傳 -1 作為哨兵
    static boolean isAVL(Node root){
        return heightOrNeg1(root) != -1;
    }
    static int heightOrNeg1(Node x){
        if (x == null) return 0;
        int hl = heightOrNeg1(x.left);
        if (hl == -1) return -1;
        int hr = heightOrNeg1(x.right);
        if (hr == -1) return -1;
        if (Math.abs(hl - hr) > 1) return -1;
        return Math.max(hl, hr) + 1;
    }
}

/*
複雜度：
- 建樹 O(n)；BST 與 AVL 檢查各 O(n)
- 總時間 O(n)，額外空間 O(h)（遞迴堆疊，h 為樹高）
*/