class MultiWayTreeNode {
    String value;
    List<MultiWayTreeNode> children;

    public MultiWayTreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(MultiWayTreeNode child) {
        children.add(child);
    }
}

public class MultiWayTreeAndDecisionTree {

    public static void dfs(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        for (MultiWayTreeNode child : node.children) {
            dfs(child);
        }
    }

    public static void bfs(MultiWayTreeNode root) {
        if (root == null) return;
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            System.out.print(node.value + " ");
            queue.addAll(node.children);
        }
    }

    public static int getHeight(MultiWayTreeNode node) {
        if (node == null) return 0;
        int maxHeight = 0;
        for (MultiWayTreeNode child : node.children) {
            maxHeight = Math.max(maxHeight, getHeight(child));
        }
        return maxHeight + 1;
    }

    public static void printNodeDegrees(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.println("節點 " + node.value + " 的度數為: " + node.children.size());
        for (MultiWayTreeNode child : node.children) {
            printNodeDegrees(child);
        }
    }

    public static void runGuessNumberGame() {
        MultiWayTreeNode root = new MultiWayTreeNode("你想的數字 > 50 嗎？");

        MultiWayTreeNode yes = new MultiWayTreeNode("你想的數字 > 75 嗎？");
        yes.addChild(new MultiWayTreeNode("76~100"));
        yes.addChild(new MultiWayTreeNode("51~75"));

        MultiWayTreeNode no = new MultiWayTreeNode("你想的數字 > 25 嗎？");
        no.addChild(new MultiWayTreeNode("26~50"));
        no.addChild(new MultiWayTreeNode("1~25"));

        root.addChild(yes); 
        root.addChild(no); 

        Scanner scanner = new Scanner(System.in);
        MultiWayTreeNode current = root;

        while (!current.children.isEmpty()) {
            System.out.println(current.value + " (yes/no)");
            String input = scanner.nextLine().toLowerCase();
            if (input.startsWith("y")) {
                current = current.children.get(0);
            } else {
                current = current.children.get(1);
            }
        }

        System.out.println("你心裡想的數字範圍是：" + current.value);
    }

    public static void main(String[] args) {
        MultiWayTreeNode root = new MultiWayTreeNode("A");
        MultiWayTreeNode b = new MultiWayTreeNode("B");
        MultiWayTreeNode c = new MultiWayTreeNode("C");
        MultiWayTreeNode d = new MultiWayTreeNode("D");
        MultiWayTreeNode e = new MultiWayTreeNode("E");
        MultiWayTreeNode f = new MultiWayTreeNode("F");

        root.addChild(b);
        root.addChild(c);
        b.addChild(d);
        b.addChild(e);
        c.addChild(f);

        System.out.println("DFS:");
        dfs(root);
        System.out.println("\nBFS:");
        bfs(root);

        System.out.println("\n\n高度: " + getHeight(root));
        System.out.println("各節點度數:");
        printNodeDegrees(root);

        System.out.println("\n--- 啟動猜數字遊戲 ---");
        runGuessNumberGame();
    }
}
