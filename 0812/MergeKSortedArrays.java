import java.util.*;

public class MergeKSortedArrays {

    static class Node {
        int value;
        int arrayIndex;  
        int elementIndex; 

        Node(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.add(new Node(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            result.add(current.value);

            if (current.elementIndex + 1 < arrays[current.arrayIndex].length) {
                int nextIndex = current.elementIndex + 1;
                minHeap.add(new Node(arrays[current.arrayIndex][nextIndex], current.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arrays1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] arrays2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] arrays3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(arrays1));
        System.out.println(mergeKSortedArrays(arrays2));
        System.out.println(mergeKSortedArrays(arrays3)); 
    }
}
