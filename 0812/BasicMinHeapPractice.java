import java.util.ArrayList;

public class BasicMinHeapPractice {
    private ArrayList<Integer> heap;

    public BasicMinHeapPractice() {
        heap = new ArrayList<>();
    }

    public void insert(int val) {
        heap.add(val);
        siftUp(heap.size() - 1);
    }

    public int extractMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty!");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return min;
    }

    public int getMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty!");
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        BasicMinHeapPractice minHeap = new BasicMinHeapPractice();

        int[] nums = {15, 10, 20, 8, 25, 5};
        for (int num : nums) {
            minHeap.insert(num);
        }

        System.out.println("ExtractMin 順序應為：5, 8, 10, 15, 20, 25");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMin() + " ");
        }
    }
}
