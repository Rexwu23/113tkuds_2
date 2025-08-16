import java.util.*;

public class PersistentAVLExercise {

    static class Node {
        final int key, height;
        final Node left, right;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = Math.max(height(left), height(right)) + 1;
        }
    }

    private List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); // 版本 0 = 空樹
    }

    private static int height(Node n) { return (n == null) ? 0 : n.height; }

    private static int getBalance(Node n) { return (n == null) ? 0 : height(n.left) - height(n.right); }

    private static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, T2, y.right));
    }

    private static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return new Node(y.key, new Node(x.key, x.left, T2), y.right);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key, null, null);

        if (key < node.key)
            node = new Node(node.key, insert(node.left, key), node.right);
        else if (key > node.key)
            node = new Node(node.key, node.left, insert(node.right, key));
        else
            return node;

        int balance = getBalance(node);
        if (balance > 1 && key < node.left.key) return rightRotate(node);
        if (balance < -1 && key > node.right.key) return leftRotate(node);
        if (balance > 1 && key > node.left.key) return rightRotate(new Node(node.key, leftRotate(node.left), node.right));
        if (balance < -1 && key < node.right.key) return leftRotate(new Node(node.key, node.left, rightRotate(node.right)));

        return node;
    }

    public void insertNewVersion(int version, int key) {
        Node base = versions.get(version);
        Node newVersion = insert(base, key);
        versions.add(newVersion);
    }

    public boolean search(int version, int key) {
        Node node = versions.get(version);
        while (node != null) {
            if (key < node.key) node = node.left;
            else if (key > node.key) node = node.right;
            else return true;
        }
        return false;
    }

    public void printVersion(int version) {
        System.out.print("Version " + version + ": ");
        inorder(versions.get(version));
        System.out.println();
    }

    private void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.key + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        PersistentAVLExercise pavl = new PersistentAVLExercise();
        pavl.insertNewVersion(0, 10); // 版本1
        pavl.insertNewVersion(1, 20); // 版本2
        pavl.insertNewVersion(2, 5);  // 版本3

        pavl.printVersion(0);
        pavl.printVersion(1);
        pavl.printVersion(2);
        pavl.printVersion(3);

        System.out.println("版本1 查找 20: " + pavl.search(1, 20));
        System.out.println("版本3 查找 20: " + pavl.search(3, 20));
    }
}