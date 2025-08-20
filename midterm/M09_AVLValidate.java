public class M09_AVLValidate {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("Valid"); 
            return;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (!isAVL(root).isAVL) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    private static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode cur = q.poll();
            if (cur == null) continue;
            // left child
            if (i < arr.length && arr[i] != -1) {
                cur.left = new TreeNode(arr[i]);
                q.add(cur.left);
            }
            i++;
            // right child
            if (i < arr.length && arr[i] != -1) {
                cur.right = new TreeNode(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    private static boolean isBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (!(min < node.val && node.val < max)) return false;
        return isBST(node.left, min, node.val) &&
               isBST(node.right, node.val, max);
    }

    static class AVLResult {
        int height;
        boolean isAVL;
        AVLResult(int h, boolean a) { height = h; isAVL = a; }
    }

    private static AVLResult isAVL(TreeNode node) {
        if (node == null) return new AVLResult(0, true);

        AVLResult left = isAVL(node.left);
        if (!left.isAVL) return new AVLResult(-1, false);

        AVLResult right = isAVL(node.right);
        if (!right.isAVL) return new AVLResult(-1, false);

        if (Math.abs(left.height - right.height) > 1) {
            return new AVLResult(-1, false);
        }
        return new AVLResult(Math.max(left.height, right.height) + 1, true);
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 建樹過程 buildTree() 使用 queue，每個節點處理一次 → O(n)。
 * 2. isBST() 每個節點訪問一次，帶上下界檢查 → O(n)。
 * 3. isAVL() 為後序遍歷，每個節點回傳一次高度 → O(n)。
 * 綜合：O(n) + O(n) + O(n) = O(n)，所以整體時間複雜度為 O(n)。
 *
 * Space Complexity: O(h) ~ O(n)
 * 說明：
 * 遞迴深度與樹高度 h 成正比；最差情況（鏈狀樹）為 O(n)，
 * 平衡情況下（AVL 高度 log n）為 O(log n)。
 */
