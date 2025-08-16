import java.util.*;

public class TreePathProblems {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 1️⃣ 找出從根到所有葉節點的路徑
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPaths(root, new ArrayList<>(), result);
        return result;
    }

    private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);

        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            dfsPaths(node.left, path, res);
            dfsPaths(node.right, path, res);
        }

        path.remove(path.size() - 1);
    }

    // 2️⃣ 是否存在從根到葉的和為目標值的路徑
    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) return false;
        if (root.left == null && root.right == null)
            return target == root.val;
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    // 3️⃣ 找出和最大的根到葉路徑
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        int left = maxRootToLeafSum(root.left);
        int right = maxRootToLeafSum(root.right);
        return root.val + Math.max(left, right);
    }

    // 4️⃣ 樹中任意兩節點間最大路徑和（包含負數，經過父節點）
    static int maxPathSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxPathSum;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) return 0;
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));
        maxPathSum = Math.max(maxPathSum, node.val + leftGain + rightGain);
        return node.val + Math.max(leftGain, rightGain);
    }

    // 主程式測試
    public static void main(String[] args) {
        /*
         * 範例樹：
         *       5
         *     /   \
         *    4     8
         *   /     / \
         *  11    13  4
         * /  \         \
         *7    2         1
         */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println("1️⃣ 所有根到葉路徑：");
        List<List<Integer>> paths = allRootToLeafPaths(root);
        for (List<Integer> path : paths) System.out.println(path);

        System.out.println("\n2️⃣ 是否存在總和為 22 的根到葉路徑？ " + hasPathSum(root, 22)); // true

        System.out.println("\n3️⃣ 最大根到葉總和: " + maxRootToLeafSum(root)); // 5+8+4+1=18

        System.out.println("\n4️⃣ 樹中任意兩節點最大路徑總和: " + maxPathSum(root)); // e.g. 48 (11+4+5+8+13)
    }
}
