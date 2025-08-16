/**
 * 練習 3：刪除操作（葉子、一個子節點、兩個子節點）+ 刪除後重平衡
 */
public class AVLDeleteExercise {
    static class Node { int key, height=1; Node left, right; Node(int k){key=k;} }
    Node root;

    int h(Node n){ return n==null?0:n.height; }
    void upd(Node n){ n.height = Math.max(h(n.left), h(n.right)) + 1; }
    int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }

    Node rotRight(Node y){ Node x=y.left, T2=x.right; x.right=y; y.left=T2; upd(y); upd(x); return x; }
    Node rotLeft(Node x){ Node y=x.right, T2=y.left; y.left=x; x.right=T2; upd(x); upd(y); return y; }
    Node rebalance(Node n){
        upd(n);
        if (bf(n) > 1) { if (bf(n.left) < 0) n.left = rotLeft(n.left); return rotRight(n); }
        if (bf(n) < -1) { if (bf(n.right) > 0) n.right = rotRight(n.right); return rotLeft(n); }
        return n;
    }

    public void insert(int k){ root = insert(root,k); }
    Node insert(Node n,int k){ if(n==null) return new Node(k); if(k<n.key) n.left=insert(n.left,k); else if(k>n.key) n.right=insert(n.right,k); else return n; return rebalance(n);}    

    public void delete(int k){ root = delete(root,k); }
    Node delete(Node n,int k){
        if (n==null) return null;
        if (k < n.key) n.left = delete(n.left,k);
        else if (k > n.key) n.right = delete(n.right,k);
        else {
            // 命中
            if (n.left==null || n.right==null) {
                n = (n.left!=null) ? n.left : n.right; // 包含葉子與單子節點
            } else {
                // 兩子：找後繼
                Node s = n.right; while (s.left!=null) s=s.left;
                n.key = s.key;
                n.right = delete(n.right, s.key);
            }
        }
        if (n==null) return null;
        return rebalance(n);
    }

    public boolean contains(int k){ Node cur=root; while(cur!=null){ if(k<cur.key)cur=cur.left; else if(k>cur.key)cur=cur.right; else return true;} return false; }

    static void inorder(Node n){ if(n==null) return; inorder(n.left); System.out.print(n.key+" "); inorder(n.right); }

    public static void main(String[] args){
        AVLDeleteExercise t = new AVLDeleteExercise();
        int[] a={20,10,30,5,15,25,40,22,50};
        for(int v:a) t.insert(v);
        inorder(t.root); System.out.println();
        t.delete(5);   inorder(t.root); System.out.println(); // 刪葉
        t.delete(40);  inorder(t.root); System.out.println(); // 刪單子
        t.delete(20);  inorder(t.root); System.out.println(); // 刪兩子
        System.out.println("contains 20? " + t.contains(20));
    }
}