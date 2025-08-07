public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;

        TreeNode root = new TreeNode(pre[ps]);
        int ri = inMap.get(pre[ps]);
        int leftSize = ri - is;

        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, ri - 1, inMap);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, ri + 1, ie, inMap);
        return root;
    }

    public TreeNode buildTreeFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;

        TreeNode root = new TreeNode(post[pe]);
        int ri = inMap.get(post[pe]);
        int leftSize = ri - is;

        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, ri - 1, inMap);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, ri + 1, ie, inMap);
        return root;
    }

    public TreeNode buildCompleteBinaryTree(int[] levelOrder) {
        if (levelOrder == null || levelOrder.length == 0) return null;

        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < levelOrder.length) {
            TreeNode curr = queue.poll();

            curr.left = new TreeNode(levelOrder[i++]);
            queue.offer(curr.left);

            if (i < levelOrder.length) {
                curr.right = new TreeNode(levelOrder[i++]);
                queue.offer(curr.right);
            }
        }

        return root;
    }

    public boolean validateInorder(TreeNode root, int[] inorder) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        if (result.size() != inorder.length) return false;
        for (int i = 0; i < inorder.length; i++) {
            if (result.get(i) != inorder[i]) return false;
        }
        return true;
    }

    private void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    public static void main(String[] args) {
        TreeReconstruction tr = new TreeReconstruction();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelOrder = {1, 2, 3, 4, 5, 6, 7};

        TreeNode root1 = tr.buildTreeFromPreIn(preorder, inorder);
        System.out.println("Pre-In Build Valid: " + tr.validateInorder(root1, inorder));

        TreeNode root2 = tr.buildTreeFromPostIn(postorder, inorder);
        System.out.println("Post-In Build Valid: " + tr.validateInorder(root2, inorder));

        TreeNode root3 = tr.buildCompleteBinaryTree(levelOrder);
        System.out.println("Complete Tree Level Root: " + root3.val); 
    }
}
