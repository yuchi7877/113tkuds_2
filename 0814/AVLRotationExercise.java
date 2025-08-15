class AVLRotationExercise {

    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1; 
        }
    }
    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    Node rotateRight(Node y){
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x; 
    }

    Node rotateLeft(Node x) {
        
        Node y = x.right;
        Node T2 = y.left;

        
        y.left = x;
        x.right = T2;

        
        updateHeight(x);
        updateHeight(y);

        return y; 
    }


    Node rotateLeftRight(Node node) {
        
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    Node rotateRightLeft(Node node) {
    
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }
    public static void main(String[] args) {
        AVLRotationExercise avl = new AVLRotationExercise();

        System.out.println("=== 測試 LL 情況（右旋） ===");
        Node rootLL = new Node(30);
        rootLL.left = new Node(20);
        rootLL.left.left = new Node(10);
        avl.updateHeight(rootLL.left);
        avl.updateHeight(rootLL);
        rootLL = avl.rotateRight(rootLL);
        avl.inOrder(rootLL);
        System.out.println("\n");

        System.out.println("=== 測試 RR 情況（左旋） ===");
        Node rootRR = new Node(10);
        rootRR.right = new Node(20);
        rootRR.right.right = new Node(30);
        avl.updateHeight(rootRR.right);
        avl.updateHeight(rootRR);
        rootRR = avl.rotateLeft(rootRR);
        avl.inOrder(rootRR);
        System.out.println("\n");

        System.out.println("=== 測試 LR 情況（左右旋） ===");
        Node rootLR = new Node(30);
        rootLR.left = new Node(10);
        rootLR.left.right = new Node(20);
        avl.updateHeight(rootLR.left);
        avl.updateHeight(rootLR);
        rootLR = avl.rotateLeftRight(rootLR);
        avl.inOrder(rootLR);
        System.out.println("\n");

        System.out.println("=== 測試 RL 情況（右左旋） ===");
        Node rootRL = new Node(10);
        rootRL.right = new Node(30);
        rootRL.right.left = new Node(20);
        avl.updateHeight(rootRL.right);
        avl.updateHeight(rootRL);
        rootRL = avl.rotateRightLeft(rootRL);
        avl.inOrder(rootRL);
        System.out.println();
    }
}
