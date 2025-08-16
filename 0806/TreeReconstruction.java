import java.util.*;

public class TreeReconstruction {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 1️⃣ 根據前序 + 中序 重建二元樹
    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = buildIndexMap(inorder);
        return buildPreIn(preorder, 0, preorder.length - 1,
                          inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPreIn(int[] pre, int ps, int pe,
                                       int[] in, int is, int ie,
                                       Map<Integer, Integer> inMap) {
        if (ps > pe) return null;

        TreeNode root = new TreeNode(pre[ps]);
        int index = inMap.get(pre[ps]);
        int leftSize = index - is;

        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, index - 1, inMap);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, index + 1, ie, inMap);
        return root;
    }

    // 2️⃣ 根據後序 + 中序 重建二元樹
    public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = buildIndexMap(inorder);
        return buildPostIn(postorder, 0, postorder.length - 1,
                           inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPostIn(int[] post, int ps, int pe,
                                        int[] in, int is, int ie,
                                        Map<Integer, Integer> inMap) {
        if (ps > pe) return null;

        TreeNode root = new TreeNode(post[pe]);
        int index = inMap.get(post[pe]);
        int leftSize = index - is;

        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, index - 1, inMap);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, index + 1, ie, inMap);
        return root;
    }

    // 3️⃣ 根據層序重建完全二元樹（假設為完全樹）
    public static TreeNode buildCompleteTreeFromLevelOrder(int[] levelOrder) {
        if (levelOrder.length == 0) return null;

        TreeNode[] nodes = new TreeNode[levelOrder.length];
        for (int i = 0; i < levelOrder.length; i++) {
            nodes[i] = new TreeNode(levelOrder[i]);
        }

        for (int i = 0; i < levelOrder.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < levelOrder.length) nodes[i].left = nodes[left];
            if (right < levelOrder.length) nodes[i].right = nodes[right];
        }

        return nodes[0];
    }

    // 4️⃣ 驗證重建結果是否正確（使用中序走訪）
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private static void inorder(TreeNode node, List<Integer> res) {
        if (node == null) return;
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    private static Map<Integer, Integer> buildIndexMap(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
    }

    // 主程式測試
    public static void main(String[] args) {
        // 測試資料
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelOrder = {1, 2, 3, 4, 5, 6, 7};

        // 1. 前序 + 中序
        TreeNode tree1 = buildFromPreIn(preorder, inorder);
        System.out.println("1️⃣ 前+中建樹後中序：" + inorderTraversal(tree1));

        // 2. 後序 + 中序
        TreeNode tree2 = buildFromPostIn(postorder, inorder);
        System.out.println("2️⃣ 後+中建樹後中序：" + inorderTraversal(tree2));

        // 3. 層序建完全二元樹
        TreeNode tree3 = buildCompleteTreeFromLevelOrder(levelOrder);
        System.out.println("3️⃣ 層序建樹後中序：" + inorderTraversal(tree3));
    }
}
