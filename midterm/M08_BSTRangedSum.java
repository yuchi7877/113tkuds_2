public class M08_BSTRangedSum {

    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int L = sc.nextInt();
        int R = sc.nextInt();

        Node root = buildTree(arr);
        int sum = rangeSum(root, L, R);

        System.out.println("Sum: " + sum);
    }
    private static Node buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;

        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node cur = q.poll();
            if (i < arr.length && arr[i] != -1) {
                cur.left = new Node(arr[i]);
                q.offer(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                cur.right = new Node(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    private static int rangeSum(Node root, int L, int R) {
        if (root == null) return 0;

        if (root.val < L) {
            return rangeSum(root.right, L, R);
        } else if (root.val > R) {
            return rangeSum(root.left, L, R);
        } else {
            return root.val
                    + rangeSum(root.left, L, R)
                    + rangeSum(root.right, L, R);
        }
    }
}
