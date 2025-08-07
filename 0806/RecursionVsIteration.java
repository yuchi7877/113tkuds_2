public class RecursionVsIteration {

    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static int binomialIterative(int n, int k) {
        int[][] dp = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }
        return dp[n][k];
    }

    public static int productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static int productIterative(int[] arr) {
        int product = 1;
        for (int value : arr) product *= value;
        return product;
    }

    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int add = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return add + countVowelsRecursive(s, index + 1);
    }

    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    public static boolean isBalancedRecursive(String s, int index, int count) {
        if (count < 0) return false;
        if (index == s.length()) return count == 0;
        if (s.charAt(index) == '(') return isBalancedRecursive(s, index + 1, count + 1);
        if (s.charAt(index) == ')') return isBalancedRecursive(s, index + 1, count - 1);
        return isBalancedRecursive(s, index + 1, count);
    }

    public static boolean isBalancedIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
    public static void main(String[] args) {
        int n = 20, k = 10;
        int[] arr = {1, 2, 3, 4, 5};
        String text = "Recursion and Iteration Comparison Example (Test)";
        String parentheses = "((())())";

        long start = System.nanoTime();
        int resultRec = binomialRecursive(n, k);
        long timeRec = System.nanoTime() - start;

        start = System.nanoTime();
        int resultIt = binomialIterative(n, k);
        long timeIt = System.nanoTime() - start;

        System.out.println("[Binomial Coefficient]");
        System.out.println("Recursive: " + resultRec + " (" + timeRec + " ns)");
        System.out.println("Iterative: " + resultIt + " (" + timeIt + " ns)");

        start = System.nanoTime();
        resultRec = productRecursive(arr, 0);
        timeRec = System.nanoTime() - start;

        start = System.nanoTime();
        resultIt = productIterative(arr);
        timeIt = System.nanoTime() - start;

        System.out.println("\n[Product of Array]");
        System.out.println("Recursive: " + resultRec + " (" + timeRec + " ns)");
        System.out.println("Iterative: " + resultIt + " (" + timeIt + " ns)");

        start = System.nanoTime();
        resultRec = countVowelsRecursive(text, 0);
        timeRec = System.nanoTime() - start;

        start = System.nanoTime();
        resultIt = countVowelsIterative(text);
        timeIt = System.nanoTime() - start;

        System.out.println("\n[Vowel Count]");
        System.out.println("Recursive: " + resultRec + " (" + timeRec + " ns)");
        System.out.println("Iterative: " + resultIt + " (" + timeIt + " ns)");

        start = System.nanoTime();
        boolean isRecBalanced = isBalancedRecursive(parentheses, 0, 0);
        timeRec = System.nanoTime() - start;

        start = System.nanoTime();
        boolean isItBalanced = isBalancedIterative(parentheses);
        timeIt = System.nanoTime() - start;

        System.out.println("\n[Parentheses Balanced]");
        System.out.println("Recursive: " + isRecBalanced + " (" + timeRec + " ns)");
        System.out.println("Iterative: " + isItBalanced + " (" + timeIt + " ns)");
    }
}
