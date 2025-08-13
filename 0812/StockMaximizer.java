import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K <= 0) {
            return 0;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;
        int n = prices.length;

        while (i < n - 1) {
            while (i < n - 1 && prices[i + 1] <= prices[i]) {
                i++;
            }
            int buy = prices[i];

            while (i < n - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            int sell = prices[i];

            if (sell > buy) {
                maxHeap.offer(sell - buy);
            }
        }

        int profit = 0;
        while (K > 0 && !maxHeap.isEmpty()) {
            profit += maxHeap.poll();
            K--;
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,4,1}, 2));
        System.out.println(maxProfit(new int[]{3,2,6,5,0,3}, 2)); 
        System.out.println(maxProfit(new int[]{1,2,3,4,5}, 2)); 
    }
}
