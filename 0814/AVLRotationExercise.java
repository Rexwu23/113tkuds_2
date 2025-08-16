/**
 * 練習 2：實作四種旋轉，並對典型不平衡情況做測試
 */
public class AVLRotationExercise {
    static class Node { int key, height=1; Node left, right; Node(int k){key=k;} }
    static int h(Node n){ return n==null?0:n.height; }
    static void upd(Node n){ n.height = Math.max(h(n.left), h(n.right)) + 1; }
    static int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }

    static Node rotRight(Node y){ Node x=y.left, T2=x.right; x.right=y; y.left=T2; upd(y); upd(x); return x; }
    static Node rotLeft(Node x){ Node y=x.right, T2=y.left; y.left=x; x.right=T2; upd(x); upd(y); return y; }

    static Node rebalance(Node n){
        upd(n);
        if (bf(n) > 1) { // L*
            if (bf(n.left) < 0) n.left = rotLeft(n.left); // LR
            return rotRight(n); // LL
        }
        if (bf(n) < -1) { // R*
            if (bf(n.right) > 0) n.right = rotRight(n.right); // RL
            return rotLeft(n); // RR
        }
        return n;
    }

    static Node insert(Node n, int k){
        if (n==null) return new Node(k);
        if (k < n.key) n.left = insert(n.left,k);
        else if (k > n.key) n.right = insert(n.right,k);
        else return n;
        return rebalance(n);
    }

    static void inorder(Node n){ if(n==null) return; inorder(n.left); System.out.print(n.key+" "); inorder(n.right);}    

    public static void main(String[] args){
        // 1) RR case -> 左旋
        Node rr=null; rr=insert(rr,10); rr=insert(rr,20); rr=insert(rr,30);
        System.out.print("RR inorder: "); inorder(rr); System.out.println();
        // 2) LL case -> 右旋
        Node ll=null; ll=insert(ll,30); ll=insert(ll,20); ll=insert(ll,10);
        System.out.print("LL inorder: "); inorder(ll); System.out.println();
        // 3) LR case
        Node lr=null; lr=insert(lr,30); lr=insert(lr,10); lr=insert(lr,20);
        System.out.print("LR inorder: "); inorder(lr); System.out.println();
        // 4) RL case
        Node rl=null; rl=insert(rl,10); rl=insert(rl,30); rl=insert(rl,20);
        System.out.print("RL inorder: "); inorder(rl); System.out.println();
    }
}