public class M11_HeapSortWithTie {

    static class Student implements Comparable<Student> {
        int score;
        int index;
        Student(int s, int i) { score = s; index = i; }

        public int compareTo(Student other) {
            if (this.score != other.score) return this.score - other.score;
            return other.index - this.index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Student(sc.nextInt(), i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i == n - 1 ? "\n" : " "));
        }
    }

    private static void heapSort(Student[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].compareTo(arr[largest]) > 0)
            largest = left;
        if (right < n && arr[right].compareTo(arr[largest]) > 0)
            largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void swap(Student[] arr, int i, int j) {
        Student tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：
 * 1. 建堆 (bottom-up) 複雜度 O(n)
 * 2. 每次取出最大元素並 heapify → n 次，每次 O(log n) → O(n log n)
 * 3. 總複雜度 O(n + n log n) = O(n log n)
 *
 * Space Complexity: O(1) in-place
 */
