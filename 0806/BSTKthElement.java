import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val;
        int count; 
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.count = 1;
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
        updateCount(node);
        return node;
    }

    public void delete(int val) {
        root = delete(root, val);
    }

    private TreeNode delete(TreeNode node, int val) {
        if (node == null) return null;
        if (val < node.val) node.left = delete(node.left, val);
        else if (val > node.val) node.right = delete(node.right, val);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TreeNode successor = findMin(node.right);
            node.val = successor.val;
            node.right = delete(node.right, successor.val);
        }
        updateCount(node);
        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private void updateCount(TreeNode node) {
        node.count = 1 + getCount(node.left) + getCount(node.right);
    }

    private int getCount(TreeNode node) {
        return node == null ? 0 : node.count;
    }

    public int kthSmallest(int k) {
        return kthSmallest(root, k);
    }

    private int kthSmallest(TreeNode node, int k) {
        if (node == null) throw new IllegalArgumentException("k超出範圍");

        int leftCount = getCount(node.left);

        if (k == leftCount + 1) return node.val;
        else if (k <= leftCount) return kthSmallest(node.left, k);
        else return kthSmallest(node.right, k - leftCount - 1);
    }

    public int kthLargest(int k) {
        if (root == null) throw new IllegalStateException("BST為空");
        int total = root.count;
        return kthSmallest(total - k + 1);
    }

    public List<Integer> rangeKtoJ(int k, int j) {
        List<Integer> result = new ArrayList<>();
        inorderRange(root, result, new int[]{0}, k, j);
        return result;
    }

    private void inorderRange(TreeNode node, List<Integer> result, int[] index, int k, int j) {
        if (node == null) return;
        inorderRange(node.left, result, index, k, j);

        index[0]++;
        if (index[0] >= k && index[0] <= j) result.add(node.val);
        if (index[0] > j) return;

        inorderRange(node.right, result, index, k, j);
    }

    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();
        int[] nums = {20, 10, 30, 5, 15, 25, 35};
        for (int n : nums) bst.insert(n);

        System.out.println("第 3 小元素: " + bst.kthSmallest(3));
        System.out.println("第 2 大元素: " + bst.kthLargest(2));
        System.out.println("第 2 小到第 5 小: " + bst.rangeKtoJ(2, 5)); 

        bst.delete(20);
        System.out.println("刪除 20 後，第 3 小: " + bst.kthSmallest(3)); 
    }
}
