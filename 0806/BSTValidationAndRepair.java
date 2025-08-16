import java.util.*;

public class BSTValidationAndRepair {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 1️⃣ 驗證是否為有效 BST（遞迴）
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    // 2️⃣ 找出 BST 中不符規則的節點（inorder 檢查）
    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        inorderCheck(root, new TreeNode[] {null}, result);
        return result;
    }

    private static void inorderCheck(TreeNode node, TreeNode[] prev, List<TreeNode> badNodes) {
        if (node == null) return;
        inorderCheck(node.left, prev, badNodes);
        if (prev[0] != null && prev[0].val > node.val) {
            badNodes.add(prev[0]);
            badNodes.add(node);
        }
        prev[0] = node;
        inorderCheck(node.right, prev, badNodes);
    }

    // 3️⃣ 修復：互換兩個錯誤節點
    public static void recoverTree(TreeNode root) {
        List<TreeNode> bad = findInvalidNodes(root);
        if (bad.size() >= 2) {
            TreeNode node1 = bad.get(0);
            TreeNode node2 = bad.get(bad.size() - 1);
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }
    }

    // 4️⃣ 移除最少節點使成 BST：回傳最小刪除數
    public static int minRemovalsToBST(TreeNode root) {
        return minRemovals(root).removalCount;
    }

    static class Result {
        boolean isBST;
        int minVal, maxVal, size, removalCount;

        Result(boolean isBST, int minVal, int maxVal, int size, int removalCount) {
            this.isBST = isBST;
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.size = size;
            this.removalCount = removalCount;
        }
    }

    private static Result minRemovals(TreeNode node) {
        if (node == null)
            return new Result(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0);

        Result left = minRemovals(node.left);
        Result right = minRemovals(node.right);

        // 合併條件：左右皆是 BST 且符合當前值的範圍
        if (left.isBST && right.isBST && node.val > left.maxVal && node.val < right.minVal) {
            return new Result(
                true,
                Math.min(node.val, left.minVal),
                Math.max(node.val, right.maxVal),
                left.size + right.size + 1,
                left.removalCount + right.removalCount
            );
        } else {
            // 當前不是 BST，就從左右選一邊保留
            return new Result(
                false,
                0, 0,
                0,
                Math.min(left.removalCount + right.size, right.removalCount + left.size)
            );
        }
    }

    // ⏱ 主程式測試
    public static void main(String[] args) {
        /*
         * 建立錯誤 BST：
         *       3
         *     /   \
         *    1     4
         *         /
         *        2   <- 2 跟 3 是錯誤互換
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("是否為有效 BST？ " + isValidBST(root)); // false

        List<TreeNode> invalids = findInvalidNodes(root);
        System.out.print("錯誤節點值：");
        for (TreeNode n : invalids) System.out.print(n.val + " ");
        System.out.println();

        recoverTree(root);
        System.out.println("修復後為 BST？ " + isValidBST(root)); // true

        // 測試需要移除幾個節點
        TreeNode invalidRoot = new TreeNode(10);
        invalidRoot.left = new TreeNode(5);
        invalidRoot.right = new TreeNode(8); // 應該比 10 小
        System.out.println("最少需要移除的節點數: " + minRemovalsToBST(invalidRoot)); // 1
    }
}
