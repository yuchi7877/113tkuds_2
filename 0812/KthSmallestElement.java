import java.util.*;

public class KthSmallestElement {

    public static int kthSmallestWithHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    public static int kthSmallestWithSort(int[] arr, int k) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy[k - 1];
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};
        int[] expected = {7, 1, 3};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int k = ks[i];

            long startHeap = System.nanoTime();
            int heapResult = kthSmallestWithHeap(arr, k);
            long endHeap = System.nanoTime();

            long startSort = System.nanoTime();
            int sortResult = kthSmallestWithSort(arr, k);
            long endSort = System.nanoTime();

            System.out.println("測試案例 " + (i + 1) + ": 陣列 = " + Arrays.toString(arr) + ", K = " + k);
            System.out.println("Heap 方法: " + heapResult + " (耗時 " + (endHeap - startHeap) + " ns)");
            System.out.println("Sort 方法: " + sortResult + " (耗時 " + (endSort - startSort) + " ns)");
            System.out.println("期望答案: " + expected[i]);
            System.out.println("------------------------------------");
        }
    }
}
