import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] heights = new long[n];
        for (int i = 0; i < n; i++) heights[i] = sc.nextLong();

        System.out.println(maxArea(heights));
    }

    public static long maxArea(long[] h) {
        int left = 0, right = h.length - 1;
        long max = 0;

        while (left < right) {
            long width = right - left;
            long height = Math.min(h[left], h[right]);
            max = Math.max(max, width * height);

            if (h[left] < h[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
