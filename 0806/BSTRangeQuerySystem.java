class BSTRangeQuerySystem {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) {
            val = v;
        }
    }

    TreeNode root;

    public void insert(int val) {
        root = insert(root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else if (val > node.val) node.right = insert(node.right, val);
        return node;
    }

    // 範圍查詢：回傳所有在[min, max] 範圍內的節點值
    public void rangeQuery(int min, int max) {
        System.out.print("範圍查詢結果: ");
        rangeQuery(root, min, max);
        System.out.println();
    }

    private void rangeQuery(TreeNode node, int min, int max) {
        if (node == null) return;
        if (node.val > min) rangeQuery(node.left, min, max);
        if (node.val >= min && node.val <= max) System.out.print(node.val + " ");
        if (node.val < max) rangeQuery(node.right, min, max);
    }

    // 範圍計數：計算在[min, max] 範圍內的節點數
    public int rangeCount(int min, int max) {
        return rangeCount(root, min, max);
    }

    private int rangeCount(TreeNode node, int min, int max) {
        if (node == null) return 0;
        if (node.val < min) return rangeCount(node.right, min, max);
        if (node.val > max) return rangeCount(node.left, min, max);
        return 1 + rangeCount(node.left, min, max) + rangeCount(node.right, min, max);
    }

    // 範圍總和：計算在[min, max] 範圍內的所有節點值總和
    public int rangeSum(int min, int max) {
        return rangeSum(root, min, max);
    }

    private int rangeSum(TreeNode node, int min, int max) {
        if (node == null) return 0;
        if (node.val < min) return rangeSum(node.right, min, max);
        if (node.val > max) return rangeSum(node.left, min, max);
        return node.val + rangeSum(node.left, min, max) + rangeSum(node.right, min, max);
    }

    // 最接近查詢：找出最接近給定 target 值的節點
    public int closestValue(int target) {
        return closestValue(root, target, root.val);
    }

    private int closestValue(TreeNode node, int target, int closest) {
        if (node == null) return closest;
        if (Math.abs(node.val - target) < Math.abs(closest - target)) {
            closest = node.val;
        }
        if (target < node.val) return closestValue(node.left, target, closest);
        else return closestValue(node.right, target, closest);
    }

    // 測試主程式
    public static void main(String[] args) {
        BSTRangeQuerySystem bst = new BSTRangeQuerySystem();
        int[] values = {10, 5, 15, 3, 7, 12, 18};
        for (int v : values) bst.insert(v);

        bst.rangeQuery(6, 13); // 範圍查詢
        System.out.println("範圍計數: " + bst.rangeCount(6, 13));
        System.out.println("範圍總和: " + bst.rangeSum(6, 13));
        System.out.println("最接近 11 的節點: " + bst.closestValue(11));
    }
}