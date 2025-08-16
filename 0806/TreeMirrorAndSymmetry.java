class TreeMirrorAndSymmetry {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) {
            val = v;
        }
    }

    // 判斷一棵樹是否為對稱樹（左右對稱）
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return t1.val == t2.val &&
               isMirror(t1.left, t2.right) &&
               isMirror(t1.right, t2.left);
    }

    // 將一棵樹轉換為其鏡像樹（原地修改）
    public static void mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    // 比較兩棵樹是否互為鏡像
    public static boolean areMirror(TreeNode t1, TreeNode t2) {
        return isMirror(t1, t2);
    }

    // 檢查 subtree 是否為 tree 的子樹
    public static boolean isSubtree(TreeNode tree, TreeNode subtree) {
        if (subtree == null) return true;
        if (tree == null) return false;
        if (isSame(tree, subtree)) return true;
        return isSubtree(tree.left, subtree) || isSubtree(tree.right, subtree);
    }

    private static boolean isSame(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isSame(a.left, b.left) && isSame(a.right, b.right);
    }

    // 前序列印（測試用）
    public static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // 測試主程式
    public static void main(String[] args) {
        /*
         * 測資：
         *       1
         *     /   \
         *    2     2
         *   / \   / \
         *  3  4  4   3
         */
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(2);
        tree.left.left = new TreeNode(3);
        tree.left.right = new TreeNode(4);
        tree.right.left = new TreeNode(4);
        tree.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(tree)); // true

        System.out.print("鏡像前前序: ");
        printPreorder(tree);
        mirror(tree);
        System.out.print("\n鏡像後前序: ");
        printPreorder(tree);
        System.out.println();

        // 建立另一棵鏡像樹來測試 areMirror
        TreeNode mirrorTree = new TreeNode(1);
        mirrorTree.left = new TreeNode(2);
        mirrorTree.right = new TreeNode(2);
        mirrorTree.left.left = new TreeNode(4);
        mirrorTree.left.right = new TreeNode(3);
        mirrorTree.right.left = new TreeNode(3);
        mirrorTree.right.right = new TreeNode(4);
        System.out.println("是否互為鏡像: " + areMirror(tree, mirrorTree)); // true

        // 測試子樹
        TreeNode sub = new TreeNode(2);
        sub.left = new TreeNode(4);
        sub.right = new TreeNode(3);
        System.out.println("是否為子樹: " + isSubtree(tree, sub)); // true
    }
}
