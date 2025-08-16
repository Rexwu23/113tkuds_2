import java.util.*;

/**
 * 練習 4：範圍查詢 rangeQuery(min,max)
 * 使用中序 + 剪枝；複雜度約 O(log n + k)
 */
public class AVLRangeQueryExercise {
    static class Node { int key, height=1; Node left,right; Node(int k){key=k;} }
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

    public List<Integer> rangeQuery(int min, int max){
        List<Integer> res = new ArrayList<>();
        range(root, min, max, res);
        return res;
    }

    private void range(Node n, int lo, int hi, List<Integer> out){
        if (n==null) return;
        if (lo < n.key) range(n.left, lo, hi, out); // 可能還有更小的
        if (lo <= n.key && n.key <= hi) out.add(n.key);
        if (n.key < hi) range(n.right, lo, hi, out); // 可能還有更大的
    }

    public static void main(String[] args){
        AVLRangeQueryExercise t = new AVLRangeQueryExercise();
        int[] a={15,10,20,8,12,17,25,6,11,13,16,27};
        for(int v:a) t.insert(v);
        System.out.println(t.rangeQuery(11,20)); // [11,12,13,15,16,17,20]
    }
}