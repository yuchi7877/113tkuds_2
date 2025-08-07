public class BSTValidationAndRepair {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        if ((min != null && node.val <= min) || (max != null && node.val >= max))
            return false;

        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    TreeNode first = null, second = null, prev = null;

    public void findInvalidNodes(TreeNode root) {
        first = second = prev = null;
        inorderFind(root);
        if (first != null && second != null) {
            System.out.println("錯誤節點: " + first.val + " 和 " + second.val);
        } else {
            System.out.println("找不到錯誤節點，可能是合法 BST。");
        }
    }

    private void inorderFind(TreeNode root) {
        if (root == null) return;

        inorderFind(root.left);

        if (prev != null && prev.val > root.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;

        inorderFind(root.right);
    }

    public void recoverBST(TreeNode root) {
        first = second = prev = null;
        inorderFind(root);

        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }

    public int minRemovalsToBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);
        return inorder.size() - lengthOfLIS(inorder);
    }

    private void getInorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        getInorder(root.left, list);
        list.add(root.val);
        getInorder(root.right, list);
    }

    private int lengthOfLIS(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int i = Collections.binarySearch(dp, num);
            if (i < 0) i = -(i + 1);
            if (i == dp.size()) dp.add(num);
            else dp.set(i, num);
        }
        return dp.size();
    }

    public static void main(String[] args) {
        BSTValidationAndRepair bst = new BSTValidationAndRepair();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(6); 
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println("是否為有效 BST: " + bst.isValidBST(root));
        bst.findInvalidNodes(root);
        bst.recoverBST(root);
        System.out.println("修復後是否為有效 BST: " + bst.isValidBST(root));
        TreeNode invalidTree = new TreeNode(10);
        invalidTree.left = new TreeNode(15); 
        invalidTree.right = new TreeNode(5); 

        System.out.println("需要移除節點數 (非 BST): " + bst.minRemovalsToBST(invalidTree));
    }
}
