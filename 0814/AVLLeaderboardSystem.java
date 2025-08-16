import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        String player;
        int score, height, size; // size = 子樹節點數
        Node left, right;

        Node(String player, int score) {
            this.player = player;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    private Node root;
    private Map<String, Integer> scoreMap = new HashMap<>();

    private int height(Node n) { return (n == null) ? 0 : n.height; }
    private int size(Node n) { return (n == null) ? 0 : n.size; }
    private int getBalance(Node n) { return (n == null) ? 0 : height(n.left) - height(n.right); }

    private void update(Node n) {
        if (n != null) {
            n.height = Math.max(height(n.left), height(n.right)) + 1;
            n.size = size(n.left) + size(n.right) + 1;
        }
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        update(y); update(x);
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        update(x); update(y);
        return y;
    }

    public void addOrUpdate(String player, int score) {
        if (scoreMap.containsKey(player)) {
            root = delete(root, player, scoreMap.get(player));
        }
        scoreMap.put(player, score);
        root = insert(root, player, score);
    }

    private Node insert(Node node, String player, int score) {
        if (node == null) return new Node(player, score);
        if (score > node.score || (score == node.score && player.compareTo(node.player) < 0))
            node.left = insert(node.left, player, score);
        else
            node.right = insert(node.right, player, score);

        update(node);
        int balance = getBalance(node);

        if (balance > 1 && score > node.left.score) return rightRotate(node);
        if (balance < -1 && score < node.right.score) return leftRotate(node);
        if (balance > 1 && score < node.left.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && score > node.right.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node delete(Node node, String player, int score) {
        if (node == null) return null;
        if (score > node.score || (score == node.score && player.compareTo(node.player) < 0))
            node.left = delete(node.left, player, score);
        else if (score < node.score || (score == node.score && player.compareTo(node.player) > 0))
            node.right = delete(node.right, player, score);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = minNode(node.right);
            node.player = min.player;
            node.score = min.score;
            node.right = delete(node.right, min.player, min.score);
        }
        update(node);
        return rebalance(node);
    }

    private Node minNode(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }

    private Node rebalance(Node node) {
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public int getRank(String player) {
        if (!scoreMap.containsKey(player)) return -1;
        return getRank(root, player, scoreMap.get(player)) + 1; // 1-based rank
    }

    private int getRank(Node node, String player, int score) {
        if (node == null) return 0;
        if (score > node.score || (score == node.score && player.compareTo(node.player) < 0))
            return getRank(node.left, player, score);
        else if (score < node.score || (score == node.score && player.compareTo(node.player) > 0))
            return size(node.left) + 1 + getRank(node.right, player, score);
        else
            return size(node.left);
    }

    public List<String> topK(int k) {
        List<String> result = new ArrayList<>();
        topK(root, k, result);
        return result;
    }

    private void topK(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        topK(node.left, k, result);
        if (result.size() < k) result.add(node.player + "(" + node.score + ")");
        topK(node.right, k, result);
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.addOrUpdate("Alice", 95);
        lb.addOrUpdate("Bob", 85);
        lb.addOrUpdate("Charlie", 100);
        lb.addOrUpdate("David", 90);

        System.out.println("前3名: " + lb.topK(3));
        System.out.println("Alice 排名: " + lb.getRank("Alice"));
    }
}