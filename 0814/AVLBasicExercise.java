
/**
 * 練習 1：實作基本操作
 * 功能：插入、搜尋、計算高度、檢查是否為有效 AVL
 */
public class AVLBasicExercise {
    private static class Node {
        int key;
        int height; // 以 1 為葉節點高度，null 的高度為 0
        Node left, right;
        Node(int key) { this.key = key; this.height = 1; }
    }

    private Node root;

    // 供外部呼叫
    public void insert(int key) { root = insert(root, key); }
    public boolean contains(int key) { return contains(root, key); }
    public int height() { return height(root); }
    public boolean isValidAVL() { return isValidAVL(root).isAVL; }

    // ===== 內部工具 =====
    private int height(Node n) { return n == null ? 0 : n.height; }
    private int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    private void update(Node n) { n.height = Math.max(height(n.left), height(n.right)) + 1; }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y; y.left = T2;
        update(y); update(x);
        return x;
    }
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x; x.right = T2;
        update(x); update(y);
        return y;
    }

    private Node rebalance(Node n) {
        update(n);
        int bf = balance(n);
        if (bf > 1) { // L*
            if (balance(n.left) < 0) n.left = rotateLeft(n.left); // LR
            return rotateRight(n); // LL
        } else if (bf < -1) { // R*
            if (balance(n.right) > 0) n.right = rotateRight(n.right); // RL
            return rotateLeft(n); // RR
        }
        return n;
    }

    private Node insert(Node n, int key) {
        if (n == null) return new Node(key);
        if (key < n.key) n.left = insert(n.left, key);
        else if (key > n.key) n.right = insert(n.right, key);
        else return n; // 不插重複
        return rebalance(n);
    }

    private boolean contains(Node n, int key) {
        while (n != null) {
            if (key < n.key) n = n.left; else if (key > n.key) n = n.right; else return true;
        }
        return false;
    }

    private static class Check { boolean isAVL; int height; int min, max; Check(boolean a,int h,int mi,int ma){isAVL=a;height=h;min=mi;max=ma;} }

    private Check isValidAVL(Node n) {
        if (n == null) return new Check(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        Check L = isValidAVL(n.left), R = isValidAVL(n.right);
        boolean bstOK = (L.max < n.key) && (n.key < R.min);
        int h = Math.max(L.height, R.height) + 1;
        int bf = L.height - R.height;
        boolean avlOK = Math.abs(bf) <= 1;
        boolean ok = L.isAVL && R.isAVL && bstOK && avlOK;
        int mi = Math.min(n.key, Math.min(L.min, R.min));
        int ma = Math.max(n.key, Math.max(L.max, R.max));
        return new Check(ok, h, mi, ma);
    }

    // Demo
    public static void main(String[] args) {
        AVLBasicExercise t = new AVLBasicExercise();
        int[] arr = {10, 20, 30, 40, 50, 25};
        for (int v : arr) t.insert(v);
        System.out.println("contains(25) = " + t.contains(25));
        System.out.println("height = " + t.height());
        System.out.println("isValidAVL = " + t.isValidAVL());
    }
}