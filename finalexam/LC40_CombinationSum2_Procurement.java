import java.util.*;

public class LC40_CombinationSum2_Procurement {
    static List<List<Integer>> res = new ArrayList<>();
    static int[] cand;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        cand = new int[n];
        for (int i = 0; i < n; i++) cand[i] = sc.nextInt();
        Arrays.sort(cand);
        backtrack(new ArrayList<>(), 0, target);
        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(comb.get(i));
            }
            System.out.println();
        }
    }

    private static void backtrack(List<Integer> path, int start, int remain) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < cand.length; i++) {
            if (i > start && cand[i] == cand[i - 1]) continue; 
            if (cand[i] > remain) break;
            path.add(cand[i]);
            backtrack(path, i + 1, remain - cand[i]); 
            path.remove(path.size() - 1);
        }
    }
}