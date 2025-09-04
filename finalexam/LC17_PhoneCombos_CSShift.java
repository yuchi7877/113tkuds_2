import java.util.*;

public class LC17_PhoneCombos_CSShift {
    static final String[] MAP = {
        "abc", "def", "ghi", "jkl",
        "mno", "pqrs", "tuv", "wxyz"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.hasNextLine() ? sc.nextLine().trim() : "";
        if (digits.isEmpty()) return;
        StringBuilder sb = new StringBuilder();
        dfs(digits, 0, sb);
    }

    private static void dfs(String digits, int idx, StringBuilder path) {
        if (idx == digits.length()) {
            System.out.println(path.toString());
            return;
        }
        String letters = MAP[digits.charAt(idx) - '2'];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(digits, idx + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}