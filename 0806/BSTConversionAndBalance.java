import java.util.*;

public class BSTConversionAndBalance {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 1️⃣ BST → 排序的雙向鏈結串列（in-place）
    static class DLLNode {
        int val;
        DLLNode prev, next;
        DLLNode(int v) { val = v; }
    }

    static DLLNode head = null, prev = null;

    public static DLLNode bstToDoublyLinkedList(TreeNode root) {
        head = null; prev = null;
        inorderBSTtoDLL(root);
        return head;
    }

    private static void inorderBSTtoDLL(TreeNode node) {
        if (node == null) return;
        inorderBSTtoDLL(node.left);
        DLLNode curr = new DLLNode(node.val);
        if (prev == null) head = curr;
        else {
            prev.next = curr;
            curr.prev = prev;
        }
        prev = curr;
        inorderBSTtoDLL(node.right);
    }

    // 2️⃣ 排序陣列 → 平衡 BST
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBST(int[] arr, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = sortedArrayToBST(arr, l, mid - 1);
        root.right = sortedArrayToBST(arr, mid + 1, r);
        return root;
    }

    // 3️⃣ 檢查 BST 是否平衡 + 回傳平衡因子（左右子樹高度差）
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int left = checkHeight(node.left);
        int right = checkHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);
    }

    // 回傳每個節點的平衡因子：leftHeight - rightHeight
    public static Map<TreeNode, Integer> getBalanceFactors(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        computeBalanceFactors(root, map);
        return map;
    }

    private static int computeBalanceFactors(TreeNode node, Map<TreeNode, Integer> map) {
        if (node == null) return 0;
        int left = computeBalanceFactors(node.left, map);
        int right = computeBalanceFactors(node.right, map);
        map.put(node, left - right);
        return 1 + Math.max(left, right);
    }

    // 4️⃣ 將每個節點值改為「大於等於它的總和」
    private static int runningSum = 0;

    public static void convertToGreaterSumTree(TreeNode root) {
        runningSum = 0;
        reverseInorder(root);
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        runningSum += node.val;
        node.val = runningSum;
        reverseInorder(node.left);
    }

    // 中序輸出（測試用）
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // 主程式測試
    public static void main(String[] args) {
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode bst = sortedArrayToBST(sorted);

        System.out.print("1️⃣ Inorder (原始BST): ");
        printInorder(bst);
        System.out.println();

        System.out.println("2️⃣ 是否為平衡BST？ " + isBalanced(bst));

        System.out.println("3️⃣ 每個節點平衡因子：");
        Map<TreeNode, Integer> factors = getBalanceFactors(bst);
        for (Map.Entry<TreeNode, Integer> e : factors.entrySet())
            System.out.println("節點 " + e.getKey().val + " → 平衡因子: " + e.getValue());

        System.out.print("4️⃣ 轉換為 Greater Sum Tree: ");
        convertToGreaterSumTree(bst);
        printInorder(bst);
        System.out.println();

        System.out.println("5️⃣ 轉換為雙向鏈結串列：");
        DLLNode head = bstToDoublyLinkedList(bst);
        for (DLLNode cur = head; cur != null; cur = cur.next) {
            System.out.print(cur.val + (cur.next != null ? " <-> " : "\n"));
        }
    }
}
