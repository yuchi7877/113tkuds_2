import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        String playerId;
        int score;
        Node left, right;
        int height;
        int subtreeSize;

        Node(String playerId, int score) {
            this.playerId = playerId;
            this.score = score;
            this.height = 1;
            this.subtreeSize = 1;
        }
    }

    Node root;
    Map<String, Integer> playerScores = new HashMap<>();

    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    int size(Node node) {
        return node == null ? 0 : node.subtreeSize;
    }

    void updateNodeInfo(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.subtreeSize = 1 + size(node.left) + size(node.right);
        }
    }

    int compare(String p1, int s1, String p2, int s2) {
        if (s1 != s2) return Integer.compare(s2, s1); 
        return p1.compareTo(p2); 
    }

    int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateNodeInfo(y);
        updateNodeInfo(x);
        return x;
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateNodeInfo(x);
        updateNodeInfo(y);
        return y;
    }

    Node insert(Node node, String playerId, int score) {
        if (node == null) return new Node(playerId, score);

        if (compare(playerId, score, node.playerId, node.score) < 0)
            node.left = insert(node.left, playerId, score);
        else if (compare(playerId, score, node.playerId, node.score) > 0)
            node.right = insert(node.right, playerId, score);
        else
            return node;

        updateNodeInfo(node);

        int balance = getBalance(node);

        if (balance > 1 && compare(playerId, score, node.left.playerId, node.left.score) < 0)
            return rotateRight(node);

        if (balance < -1 && compare(playerId, score, node.right.playerId, node.right.score) > 0)
            return rotateLeft(node);

        if (balance > 1 && compare(playerId, score, node.left.playerId, node.left.score) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && compare(playerId, score, node.right.playerId, node.right.score) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    Node delete(Node node, String playerId, int score) {
        if (node == null) return null;

        if (compare(playerId, score, node.playerId, node.score) < 0)
            node.left = delete(node.left, playerId, score);
        else if (compare(playerId, score, node.playerId, node.score) > 0)
            node.right = delete(node.right, playerId, score);
        else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node successor = minValueNode(node.right);
                node.playerId = successor.playerId;
                node.score = successor.score;
                node.right = delete(node.right, successor.playerId, successor.score);
            }
        }

        if (node == null) return null;

        updateNodeInfo(node);

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rotateRight(node);

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0)
            return rotateLeft(node);

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void addPlayer(String playerId, int score) {
        if (playerScores.containsKey(playerId)) {
            System.out.println("玩家已存在，請用 updateScore 更新分數");
            return;
        }
        playerScores.put(playerId, score);
        root = insert(root, playerId, score);
    }

    public void updateScore(String playerId, int newScore) {
        if (!playerScores.containsKey(playerId)) {
            System.out.println("玩家不存在");
            return;
        }
        int oldScore = playerScores.get(playerId);
        root = delete(root, playerId, oldScore);
        root = insert(root, playerId, newScore);
        playerScores.put(playerId, newScore);
    }

    public int getRank(String playerId) {
        if (!playerScores.containsKey(playerId)) return -1;
        int score = playerScores.get(playerId);
        return getRankHelper(root, playerId, score) + 1;
    }

    private int getRankHelper(Node node, String playerId, int score) {
        if (node == null) return 0;
        int cmp = compare(playerId, score, node.playerId, node.score);
        if (cmp < 0) { 
            return getRankHelper(node.left, playerId, score);
        } else if (cmp > 0) { 
            return size(node.left) + 1 + getRankHelper(node.right, playerId, score);
        } else {
            return size(node.left); 
        }
    }

    public List<String> getTopK(int k) {
        List<String> result = new ArrayList<>();
        getTopKHelper(root, k, result);
        return result;
    }

    private void getTopKHelper(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        getTopKHelper(node.left, k, result);
        if (result.size() < k) result.add(node.playerId + "(" + node.score + ")");
        getTopKHelper(node.right, k, result);
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addPlayer("Alice", 50);
        leaderboard.addPlayer("Bob", 70);
        leaderboard.addPlayer("Charlie", 60);
        leaderboard.addPlayer("Dave", 80);

        System.out.println("前 3 名: " + leaderboard.getTopK(3));
        System.out.println("Alice 排名: " + leaderboard.getRank("Alice"));

        leaderboard.updateScore("Alice", 90);
        System.out.println("更新 Alice 分數後前 3 名: " + leaderboard.getTopK(3));
        System.out.println("Alice 排名: " + leaderboard.getRank("Alice"));
    }
}
