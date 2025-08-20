public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int idx;

        Item(String name, int qty, int idx) {
            this.name = name;
            this.qty = qty;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();

        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            if (a.qty != b.qty) return a.qty - b.qty; 
            return a.idx - b.idx; 
        });

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            Item item = new Item(name, qty, i);

            pq.offer(item);
            if (pq.size() > K) {
                pq.poll();
            }
        }

        List<Item> result = new ArrayList<>(pq);
        result.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty;
            return a.idx - b.idx;
        });

        for (Item item : result) {
            System.out.println(item.name + " " + item.qty);
        }
    }
}
/*
 * Time Complexity:
 * 1. 對每筆資料插入 Heap：O(n log K)，因為 Heap 大小最多為 K。
 * 2. 輸出時排序 K 筆：O(K log K)。
 * 3. 總複雜度：O(n log K + K log K)。因 K << n，主要為 O(n log K)。
 *
 * 與直接全排序 O(n log n) 相比，當 K 遠小於 n 時更有效率。
 *
 * 穩定性說明：
 * - 若數量相同，依照輸入順序輸出。
 */