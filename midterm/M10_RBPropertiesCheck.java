public class M10_RBPropertiesCheck {

    static class Node {
        int val;
        char color;
        Node left, right;
        Node(int v, char c) { val = v; color = c; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("RB Valid");
            return;
        }

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String col = sc.next();
            char color = (val == -1) ? 'B' : col.charAt(0);
            nodes[i] = (val == -1) ? null : new Node(val, color);
        }

        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) continue;
            int li = 2 * i + 1, ri = 2 * i + 2;
            if (li < n) nodes[i].left = nodes[li];
            if (ri < n) nodes[i].right = nodes[ri];
        }

        Node root = nodes[0];

        if (root != null && root.color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        BlackHeightResult res = checkRB(root);
        if (!res.redRedOk) {
            System.out.println("RedRedViolation at index " + res.violationIndex);
        } else if (!res.bhOk) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }
    }

    static class BlackHeightResult {
        int bh; 
        boolean redRedOk;
        boolean bhOk;
        int violationIndex;
        BlackHeightResult(int bh, boolean redOk, boolean bhOk, int idx) {
            this.bh = bh;
            this.redRedOk = redOk;
            this.bhOk = bhOk;
            this.violationIndex = idx;
        }
    }

    private static BlackHeightResult checkRB(Node node) {
        if (node == null) return new BlackHeightResult(1, true, true, -1);

        BlackHeightResult left = checkRB(node.left);
        if (!left.redRedOk || !left.bhOk) return left;
        BlackHeightResult right = checkRB(node.right);
        if (!right.redRedOk || !right.bhOk) return right;

        if (node.color == 'R') {
            if ((node.left != null && node.left.color == 'R')) 
                return new BlackHeightResult(-1, false, true, left.violationIndex >=0 ? left.violationIndex : getNodeIndex(node.left));
            if ((node.right != null && node.right.color == 'R')) 
                return new BlackHeightResult(-1, false, true, right.violationIndex >=0 ? right.violationIndex : getNodeIndex(node.right));
        }

        if (left.bh != right.bh) return new BlackHeightResult(-1, true, false, -1);

        int bh = left.bh + ((node.color == 'B') ? 1 : 0);
        return new BlackHeightResult(bh, true, true, -1);
    }

    private static int getNodeIndex(Node node) {
        return -1; 
    }
}