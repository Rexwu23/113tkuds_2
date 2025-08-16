import java.util.*;

public class LevelOrderTraversalVariations {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) {
            val = v;
        }
    }

    // 1. 每層節點放到不同 List 中
    public static List<List<Integer>> levelOrderGrouped(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                layer.add(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(layer);
        }

        return res;
    }

    // 2. 之字形層序走訪（zigzag）
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (leftToRight) level.addLast(cur.val);
                else level.addFirst(cur.val);

                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }

            res.add(level);
            leftToRight = !leftToRight;
        }

        return res;
    }

    // 3. 每層最後一個節點
    public static List<Integer> lastNodeEachLevel(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode last = null;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                last = cur;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(last.val);
        }

        return res;
    }

    // 4. 垂直層序走訪（vertical order traversal）
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> colQ = new LinkedList<>();

        q.offer(root);
        colQ.offer(0);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = colQ.poll();

            map.computeIfAbsent(col, x -> new ArrayList<>()).add(node.val);

            if (node.left != null) {
                q.offer(node.left);
                colQ.offer(col - 1);
            }
            if (node.right != null) {
                q.offer(node.right);
                colQ.offer(col + 1);
            }
        }

        return new ArrayList<>(map.values());
    }

    // 主程式測試
    public static void main(String[] args) {
        /*
         * 測試樹：
         *       1
         *     /   \
         *    2     3
         *   / \   / \
         *  4  5  6   7
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);  root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);  root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);

        System.out.println("1️⃣ 每層節點分開：");
        for (List<Integer> level : levelOrderGrouped(root)) System.out.println(level);

        System.out.println("\n2️⃣ 之字形層序：");
        for (List<Integer> level : zigzagLevelOrder(root)) System.out.println(level);

        System.out.println("\n3️⃣ 每層最後節點：");
        System.out.println(lastNodeEachLevel(root));

        System.out.println("\n4️⃣ 垂直層序：");
        for (List<Integer> vertical : verticalOrder(root)) System.out.println(vertical);
    }
}
