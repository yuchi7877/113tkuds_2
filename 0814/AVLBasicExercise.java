class AVLBasicExercise {

    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            height = 1; 
        }
    }

    Node root;

    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

    
        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }


    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }
    Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else 
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rotateRight(node);

        if (balance < -1 && key > node.right.key)
            return rotateLeft(node);

        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node; 
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    boolean search(Node node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        if (key < node.key) return search(node.left, key);
        return search(node.right, key);
    }

    public boolean search(int key) {
        return search(root, key);
    }
    public int getHeight() {
        return height(root);
    }

    boolean isAVL(Node node) {
        if (node == null) return true;
        int balance = getBalance(node);
        if (balance < -1 || balance > 1) return false;
        return isAVL(node.left) && isAVL(node.right);
    }

    public boolean isAVL() {
        return isAVL(root);
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLBasicExercise avl = new AVLBasicExercise();

        int[] nums = { 10, 20, 30, 40, 50, 25 };
        for (int n : nums) {
            avl.insert(n);
        }

        System.out.print("中序遍歷: ");
        avl.inOrder(avl.root);
        System.out.println();

        System.out.println("搜尋 25: " + avl.search(25));
        System.out.println("樹高度: " + avl.getHeight());
        System.out.println("是否為有效 AVL 樹: " + avl.isAVL());
    }
}
