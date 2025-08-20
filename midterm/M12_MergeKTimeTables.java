public class M12_MergeKTimeTables {

    static class Entry implements Comparable<Entry> {
        int time;
        int listId;
        int index;
        Entry(int t, int l, int i) { time = t; listId = l; index = i; }

        public int compareTo(Entry other) {
            return this.time - other.time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();

        for (int k = 0; k < K; k++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) arr[i] = sc.nextInt();
            lists.add(arr);
        }

        List<Integer> merged = mergeKLists(lists);

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i));
            if (i != merged.size() - 1) System.out.print(" ");
        }
    }

    private static List<Integer> mergeKLists(List<int[]> lists) {
        PriorityQueue<Entry> pq = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).length > 0) {
                pq.offer(new Entry(lists.get(i)[0], i, 0));
            }
        }

        while (!pq.isEmpty()) {
            Entry e = pq.poll();
            result.add(e.time);
            int nextIdx = e.index + 1;
            if (nextIdx < lists.get(e.listId).length) {
                pq.offer(new Entry(lists.get(e.listId)[nextIdx], e.listId, nextIdx));
            }
        }

        return result;
    }
}

