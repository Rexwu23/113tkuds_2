import java.util.*;

public class BSTKthElement {
    static class TreeNode {
        int val;
        TreeNode left, right;
        int size; // 左子樹 + 自己 + 右子樹的總節點數

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    TreeNode root;

    // 更新節點大小
    private int getSize(TreeNode node) {
        return (node == null) ? 0 : node.size;
    }

    private void updateSize(TreeNode node) {
        if (node != null) {
            node.size = 1 + getSize(node.left) + getSize(node.right);
        }
    }

    // 動態插入
    public void insert(int val) {
        root = insert(root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        updateSize(node);
        return node;
    }

    // 動態刪除
    public void delete(int val) {
        root = delete(root, val);
    }

    private TreeNode delete(TreeNode node, int val) {
        if (node == null) return null;
        if (val < node.val) node.left = delete(node.left, val);
        else if (val > node.val) node.right = delete(node.right, val);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            TreeNode successor = findMin(node.right);
            node.val = successor.val;
            node.right = delete(node.right, successor.val);
        }
        updateSize(node);
        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 找出第k小元素
    public int kthSmallest(int k) {
        return kthSmallest(root, k);
    }

    private int kthSmallest(TreeNode node, int k) {
        if (node == null) throw new NoSuchElementException();
        int leftSize = getSize(node.left);
        if (k <= leftSize) return kthSmallest(node.left, k);
        else if (k == leftSize + 1) return node.val;
        else return kthSmallest(node.right, k - leftSize - 1);
    }

    // 找出第k大元素
    public int kthLargest(int k) {
        int total = getSize(root);
        return kthSmallest(total - k + 1);
    }

    // 找出第k小到第j小元素（含）
    public List<Integer> rangeKtoJ(int k, int j) {
        List<Integer> result = new ArrayList<>();
        inOrderRange(root, result, k, j, new int[]{0});
        return result;
    }

    private void inOrderRange(TreeNode node, List<Integer> list, int k, int j, int[] count) {
        if (node == null) return;
        inOrderRange(node.left, list, k, j, count);
        count[0]++;
        if (count[0] >= k && count[0] <= j) {
            list.add(node.val);
        }
        if (count[0] > j) return;
        inOrderRange(node.right, list, k, j, count);
    }

    // 主程式測試
    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();
        int[] nums = {20, 10, 30, 5, 15, 25, 35};
        for (int n : nums) bst.insert(n);

        System.out.println("第3小: " + bst.kthSmallest(3)); // 15
        System.out.println("第2大: " + bst.kthLargest(2)); // 30
        System.out.println("第2小到第5小: " + bst.rangeKtoJ(2, 5)); // [10, 15, 20, 25]

        bst.delete(15);
        System.out.println("刪除15後第3小: " + bst.kthSmallest(3)); // 20
    }
}
