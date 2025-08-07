class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class TreePathProblems {

    public List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsAllPaths(root, new ArrayList<>(), result);
        return result;
    }

    private void dfsAllPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);

        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            dfsAllPaths(node.left, path, result);
            dfsAllPaths(node.right, path, result);
        }

        path.remove(path.size() - 1); 
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public List<Integer> maxRootToLeafPath(TreeNode root) {
        List<Integer> bestPath = new ArrayList<>();
        dfsMaxPath(root, new ArrayList<>(), 0, new int[]{Integer.MIN_VALUE}, bestPath);
        return bestPath;
    }

    private void dfsMaxPath(TreeNode node, List<Integer> path, int sum, int[] maxSum, List<Integer> bestPath) {
        if (node == null) return;

        path.add(node.val);
        sum += node.val;

        if (node.left == null && node.right == null) {
            if (sum > maxSum[0]) {
                maxSum[0] = sum;
                bestPath.clear();
                bestPath.addAll(path);
            }
        } else {
            dfsMaxPath(node.left, path, sum, maxSum, bestPath);
            dfsMaxPath(node.right, path, sum, maxSum, bestPath);
        }

        path.remove(path.size() - 1); 
    }

    private int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathDown(root);
        return maxPathSum;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;

        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));

        maxPathSum = Math.max(maxPathSum, left + right + node.val);

        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreePathProblems tpp = new TreePathProblems();

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);

        System.out.println("1. 所有根到葉路徑：" + tpp.allRootToLeafPaths(root));
        System.out.println("2. 是否存在總和為 18 的路徑：" + tpp.hasPathSum(root, 18));
        System.out.println("3. 最大和根到葉路徑：" + tpp.maxRootToLeafPath(root));
        System.out.println("4. 任兩節點最大路徑和：" + tpp.maxPathSum(root));
    }
}
