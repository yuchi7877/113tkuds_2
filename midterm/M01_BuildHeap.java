public class M01_BuildHeap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        buildHeap(arr, type);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) System.out.print(" ");
        }
    }

    private static void buildHeap(int[] arr, String type) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, type);
        }
    }

    private static void heapifyDown(int[] arr, int n, int i, String type) {
        int extreme = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (type.equals("max")) {
            if (left < n && arr[left] > arr[extreme]) extreme = left;
            if (right < n && arr[right] > arr[extreme]) extreme = right;
        } else { // min-heap
            if (left < n && arr[left] < arr[extreme]) extreme = left;
            if (right < n && arr[right] < arr[extreme]) extreme = right;
        }

        if (extreme != i) {
            int temp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = temp;

            heapifyDown(arr, n, extreme, type);
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. heapifyDown 的最壞時間是 O(log n)，但不是每個節點都下沉到底。
 * 2. 高度 h 的節點最多有 n / 2^(h+1) 個，每個需要 O(h) 次操作。
 * 3. Σ (n/2^(h+1) * h) 從 h=0 到 log n，收斂到 O(n)。
 *    因此，自底向上建堆的時間複雜度為 O(n)，比逐一 insert 的 O(n log n) 更快。
 */
