public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode leftMirror = mirror(root.left);
        TreeNode rightMirror = mirror(root.right);

        root.left = rightMirror;
        root.right = leftMirror;
        return root;
    }

    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isSameTree(t1.left, t2.left)
                && isSameTree(t1.right, t2.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(root)); 

        TreeNode mirrored = mirror(copyTree(root));
        System.out.println("鏡像後仍為對稱樹: " + isSymmetric(mirrored));

        TreeNode other = copyTree(root);
        mirror(other);
        System.out.println("兩棵樹是否互為鏡像: " + isMirror(root, other)); 

        TreeNode sub = new TreeNode(2);
        sub.left = new TreeNode(3);
        sub.right = new TreeNode(4);
        System.out.println("是否為子樹: " + isSubtree(root, sub)); 
    }

    public static TreeNode copyTree(TreeNode root) {
        if (root == null) return null;
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = copyTree(root.left);
        newNode.right = copyTree(root.right);
        return newNode;
    }
}
