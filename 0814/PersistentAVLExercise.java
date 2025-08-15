public class PersistentAVLExercise {

    static class Node {
        final int key;
        final Node left;
        final Node right;
        final int height;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
        }
    }

    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); 
    }

    private static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private static int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private static Node rotateRight(Node y) {
        Node x = y.left;
        Node newRight = new Node(y.key, x.right, y.right);
        return new Node(x.key, x.left, newRight);
    }

    private static Node rotateLeft(Node x) {
        Node y = x.right;
        Node newLeft = new Node(x.key, x.left, y.left);
        return new Node(y.key, newLeft, y.right);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key, null, null);

        if (key < node.key) {
            node = new Node(node.key, insert(node.left, key), node.right);
        } else if (key > node.key) {
            node = new Node(node.key, node.left, insert(node.right, key));
        } else {
            return node; 
        }

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) return rotateRight(node);
        if (balance < -1 && key > node.right.key) return rotateLeft(node);
        if (balance > 1 && key > node.left.key) {
            Node newLeft = rotateLeft(node.left);
            return rotateRight(new Node(node.key, newLeft, node.right));
        }
        if (balance < -1 && key < node.right.key) {
            Node newRight = rotateRight(node.right);
            return rotateLeft(new Node(node.key, node.left, newRight));
        }

        return node;
    }

    public void insert(int versionIndex, int key) {
        if (versionIndex < 0 || versionIndex >= versions.size()) {
            throw new IllegalArgumentException("無效版本編號");
        }
        Node root = versions.get(versionIndex);
        Node newRoot = insert(root, key);
        versions.add(newRoot);
    }

    public boolean search(int versionIndex, int key) {
        if (versionIndex < 0 || versionIndex >= versions.size()) {
            throw new IllegalArgumentException("無效版本編號");
        }
        Node node = versions.get(versionIndex);
        while (node != null) {
            if (key < node.key) node = node.left;
            else if (key > node.key) node = node.right;
            else return true;
        }
        return false;
    }

