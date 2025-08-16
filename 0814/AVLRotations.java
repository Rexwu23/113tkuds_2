public class AVLRotations {

    // 定義 AVLNode 類別
    public static class AVLNode {
        int key;
        int height;
        AVLNode left, right;

        AVLNode(int key) {
            this.key = key;
            this.height = 1;
        }

        void updateHeight() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            this.height = 1 + Math.max(leftHeight, rightHeight);
        }

        // 計算平衡因子
        int getBalance() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            return leftHeight - rightHeight;
        }
    }

    // 右旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.updateHeight();
        x.updateHeight();

        return x; // 新的根節點
    }

    // 左旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.updateHeight();
        y.updateHeight();

        return y; // 新的根節點
    }

    public static void main(String[] args) {
        // 測試右旋
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.left.left = new AVLNode(10);

        root.left.left.updateHeight();
        root.left.updateHeight();
        root.updateHeight();

        System.out.println("右旋前：");
        printTree(root);

        root = rightRotate(root);

        System.out.println("右旋後：");
        printTree(root);

        // 測試左旋
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(20);
        root2.right.right = new AVLNode(30);

        root2.right.right.updateHeight();
        root2.right.updateHeight();
        root2.updateHeight();

        System.out.println("左旋前：");
        printTree(root2);

        root2 = leftRotate(root2);

        System.out.println("左旋後：");
        printTree(root2);
    }

    // 輔助函式：前序遍歷並印出節點資料與高度
    public static void printTree(AVLNode node) {
        if (node == null) return;
        System.out.println("資料: " + node.key + ", 高度: " + node.height + ", 平衡因子: " + node.getBalance());
        printTree(node.left);
        printTree(node.right);
    }
}