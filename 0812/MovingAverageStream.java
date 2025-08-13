import java.util.*;

public class MovingAverage {
    private int size;              
    private Deque<Integer> window;   
    private double sum;               

    private PriorityQueue<Integer> maxHeap; 
    private PriorityQueue<Integer> minHeap; 

    private TreeMap<Integer, Integer> freq; 

    public MovingAverage(int size) {
        this.size = size;
        this.window = new ArrayDeque<>();
        this.sum = 0;

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();

        this.freq = new TreeMap<>();
    }

    public double next(int val) {
        window.addLast(val);
        sum += val;
        addNum(val);
        freq.put(val, freq.getOrDefault(val, 0) + 1);

        if (window.size() > size) {
            int removed = window.removeFirst();
            sum -= removed;
            removeNum(removed);
            int count = freq.get(removed);
            if (count == 1) freq.remove(removed);
            else freq.put(removed, count - 1);
        }

        return sum / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        }
    }

    public int getMin() {
        return freq.firstKey();
    }

    public int getMax() {
        return freq.lastKey();
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(1));   
        System.out.println(ma.next(10)); 
        System.out.println(ma.next(3));   
        System.out.println(ma.next(5));  
        System.out.println("Median: " + ma.getMedian()); 
        System.out.println("Min: " + ma.getMin());     
        System.out.println("Max: " + ma.getMax());    
    }
}
