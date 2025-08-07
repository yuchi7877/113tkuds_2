public class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    private TreeNode root;

    public void insert(int val) {
        root = insert(root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        return node;
    }

    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQuery(root, min, max, result);
        return result;
    }

    private void rangeQuery(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;

        if (node.val > min) rangeQuery(node.left, min, max, result);
        if (node.val >= min && node.val <= max) result.add(node.val);
        if (node.val < max) rangeQuery(node.right, min, max, result);
    }

    public int rangeCount(int min, int max) {
        return rangeCount(root, min, max);
    }

    private int rangeCount(TreeNode node, int min, int max) {
        if (node == null) return 0;
        if (node.val < min) return rangeCount(node.right, min, max);
        if (node.val > max) return rangeCount(node.left, min, max);
        return 1 + rangeCount(node.left, min, max) + rangeCount(node.right, min, max);
    }

    public int rangeSum(int min, int max) {
        return rangeSum(root, min, max);
    }

    private int rangeSum(TreeNode node, int min, int max) {
        if (node == null) return 0;
        if (node.val < min) return rangeSum(node.right, min, max);
        if (node.val > max) return rangeSum(node.left, min, max);
        return node.val + rangeSum(node.left, min, max) + rangeSum(node.right, min, max);
    }

    public int findClosest(int target) {
        return findClosest(root, target, root.val);
    }

    private int findClosest(TreeNode node, int target, int closest) {
        if (node == null) return closest;
        if (Math.abs(node.val - target) < Math.abs(closest - target)) {
            closest = node.val;
        }

        if (target < node.val) {
            return findClosest(node.left, target, closest);
        } else if (target > node.val) {
            return findClosest(node.right, target, closest);
        } else {
            return node.val; 
        }
    }

    public static void main(String[] args) {
        BSTRangeQuerySystem bst = new BSTRangeQuerySystem();

        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int v : values) bst.insert(v);

        System.out.println("範圍查詢 [10, 30]: " + bst.rangeQuery(10, 30));
        System.out.println("範圍計數 [10, 30]: " + bst.rangeCount(10, 30));
        System.out.println("範圍總和 [10, 30]: " + bst.rangeSum(10, 30));
        System.out.println("最接近 12 的節點: " + bst.findClosest(12));
        System.out.println("最接近 28 的節點: " + bst.findClosest(28));
    }
}
