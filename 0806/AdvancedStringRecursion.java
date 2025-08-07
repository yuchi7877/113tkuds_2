public class AdvancedStringRecursion {
    public static void permute(String str) {
        permuteHelper("", str);
    }

    private static void permuteHelper(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                permuteHelper(prefix + remaining.charAt(i),
                              remaining.substring(0, i) + remaining.substring(i + 1));
            }
        }
    }

    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return true;
        if (text.length() < pattern.length()) return false;
        if (text.startsWith(pattern)) return true;
        return isMatch(text.substring(1), pattern);
    }

    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, 0, new HashSet<>());
    }

    private static String removeDuplicatesHelper(String str, int index, Set<Character> seen) {
        if (index == str.length()) return "";
        char current = str.charAt(index);
        if (seen.contains(current)) {
            return removeDuplicatesHelper(str, index + 1, seen);
        } else {
            seen.add(current);
            return current + removeDuplicatesHelper(str, index + 1, seen);
        }
    }

    public static void substrings(String str) {
        substringsHelper(str, "", 0);
    }

    private static void substringsHelper(String str, String current, int index) {
        if (index == str.length()) {
            if (!current.isEmpty()) System.out.println(current);
            return;
        }
        substringsHelper(str, current + str.charAt(index), index + 1);
        substringsHelper(str, current, index + 1);
    }

    public static void main(String[] args) {
        System.out.println("=== 所有排列 ===");
        permute("abc");

        System.out.println("\n=== 字串匹配 ===");
        System.out.println(isMatch("helloworld", "world"));
        System.out.println(isMatch("helloworld", "abc"));

        System.out.println("\n=== 移除重複字元 ===");
        System.out.println(removeDuplicates("banana"));

        System.out.println("\n=== 所有子字串 ===");
        substrings("abc");
    }
}
