import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // 1️⃣ 多路樹節點類別（任意多個子節點）
    static class MultiWayNode {
        String val;
        List<MultiWayNode> children;

        MultiWayNode(String v) {
            this.val = v;
            this.children = new ArrayList<>();
        }

        void addChild(MultiWayNode child) {
            children.add(child);
        }
    }

    // 2️⃣ 深度優先走訪（DFS）
    public static void dfs(MultiWayNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        for (MultiWayNode child : root.children) {
            dfs(child);
        }
    }

    // 3️⃣ 廣度優先走訪（BFS）
    public static void bfs(MultiWayNode root) {
        if (root == null) return;
        Queue<MultiWayNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayNode curr = queue.poll();
            System.out.print(curr.val + " ");
            for (MultiWayNode child : curr.children) {
                queue.offer(child);
            }
        }
    }

    // 4️⃣ 計算多路樹高度
    public static int getHeight(MultiWayNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (MultiWayNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        return 1 + maxChildHeight;
    }

    // 5️⃣ 印出每個節點的度數（子節點數）
    public static void printDegrees(MultiWayNode root) {
        if (root == null) return;
        System.out.println("節點 " + root.val + " 的度數：" + root.children.size());
        for (MultiWayNode child : root.children) {
            printDegrees(child);
        }
    }

    // 6️⃣ 模擬簡單決策樹（猜數字遊戲）
    static class DecisionNode {
        String question;
        DecisionNode yes, no;

        DecisionNode(String q) {
            this.question = q;
        }
    }

    public static void simulateDecisionTree(DecisionNode root, Scanner sc) {
        DecisionNode node = root;
        while (node.yes != null || node.no != null) {
            System.out.print(node.question + " (y/n): ");
            String ans = sc.next().trim().toLowerCase();
            node = ans.startsWith("y") ? node.yes : node.no;
        }
        System.out.println("👉 答案是：" + node.question);
    }

    // 主程式測試
    public static void main(String[] args) {
        // 測試多路樹
        MultiWayNode root = new MultiWayNode("A");
        MultiWayNode b = new MultiWayNode("B");
        MultiWayNode c = new MultiWayNode("C");
        MultiWayNode d = new MultiWayNode("D");
        MultiWayNode e = new MultiWayNode("E");
        MultiWayNode f = new MultiWayNode("F");

        root.addChild(b);
        root.addChild(c);
        b.addChild(d);
        b.addChild(e);
        c.addChild(f);

        System.out.println("1️⃣ DFS 深度優先走訪：");
        dfs(root); // A B D E C F
        System.out.println("\n2️⃣ BFS 廣度優先走訪：");
        bfs(root); // A B C D E F

        System.out.println("\n3️⃣ 樹的高度：" + getHeight(root)); // 3
        System.out.println("4️⃣ 節點度數列表：");
        printDegrees(root);

        System.out.println("\n5️⃣ 決策樹（猜數字）模擬：");

        // 建立簡單決策樹
        DecisionNode dRoot = new DecisionNode("數字 > 5 嗎？");
        dRoot.yes = new DecisionNode("數字 > 8 嗎？");
        dRoot.no = new DecisionNode("數字 < 3 嗎？");
        dRoot.yes.yes = new DecisionNode("9");
        dRoot.yes.no = new DecisionNode("6");
        dRoot.no.yes = new DecisionNode("2");
        dRoot.no.no = new DecisionNode("4");

        // 模擬決策（輸入 y/n）
        Scanner sc = new Scanner(System.in);
        simulateDecisionTree(dRoot, sc);
        sc.close();
    }
}
