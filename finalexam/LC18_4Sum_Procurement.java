import java.util.*;

public class LC18_4Sum_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        Arrays.sort(nums);
        List<List<Integer>> ans = fourSum(nums, target);
        for (List<Integer> quad : ans) {
            for (int i = 0; i < quad.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(quad.get(i));
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int L = j + 1, R = n - 1;
                while (L < R) {
                    long sum = (long) nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        L++;
                        R--;
                        while (L < R && nums[L] == nums[L - 1]) L++;
                        while (L < R && nums[R] == nums[R + 1]) R--;
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }
        return res;
    }
}