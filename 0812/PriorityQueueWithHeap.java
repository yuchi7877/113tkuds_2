import java.util.ArrayList;
import java.util.List;

class Task {
    String name;
    int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return name + " (優先級: " + priority + ")";
    }
}

public class PriorityQueueWithHeap {
    private List<Task> heap;

    public PriorityQueueWithHeap() {
        heap = new ArrayList<>();
    }

    public void addTask(String name, int priority) {
        Task newTask = new Task(name, priority);
        heap.add(newTask);
        heapifyUp(heap.size() - 1);
    }

    public Task executeNext() {
        if (isEmpty()) return null;
        Task top = heap.get(0);
        Task last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return top;
    }

    public Task peek() {
        return isEmpty() ? null : heap.get(0);
    }

    public void changePriority(String name, int newPriority) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).name.equals(name)) {
                int oldPriority = heap.get(i).priority;
                heap.get(i).priority = newPriority;
                if (newPriority > oldPriority) {
                    heapifyUp(i);
                } else {
                    heapifyDown(i);
                }
                return;
            }
        }
        System.out.println("任務 " + name + " 不存在！");
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).priority > heap.get(parent).priority) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int largest = index;

            if (left < size && heap.get(left).priority > heap.get(largest).priority) {
                largest = left;
            }
            if (right < size && heap.get(right).priority > heap.get(largest).priority) {
                largest = right;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("下一個要執行的任務: " + pq.peek());
        System.out.println("執行: " + pq.executeNext());
        System.out.println("執行: " + pq.executeNext());
        System.out.println("執行: " + pq.executeNext());

        pq.addTask("清理", 2);
        pq.addTask("安全檢查", 4);
        pq.changePriority("清理", 6);
        System.out.println("下一個要執行的任務: " + pq.peek());
    }
}
