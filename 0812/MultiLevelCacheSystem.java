import java.util.*;

public class MultiLevelCacheSystem<K, V> {

    class CacheItem {
        K key;
        V value;
        int freq;        
        long timestamp;  

        CacheItem(K key, V value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.timestamp = System.nanoTime();
        }
    }

    class CacheLevel {
        int capacity;
        int cost;
        Map<K, CacheItem> map;
        PriorityQueue<CacheItem> heap; 

        CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.map = new HashMap<>();
            this.heap = new PriorityQueue<>((a, b) -> {
                double scoreA = (double)a.freq / cost;
                double scoreB = (double)b.freq / cost;
                if (scoreA != scoreB) return Double.compare(scoreA, scoreB);
                return Long.compare(a.timestamp, b.timestamp);
            });
        }

        boolean isFull() {
            return map.size() >= capacity;
        }

        void put(CacheItem item) {
            if (!map.containsKey(item.key) && isFull()) {
                CacheItem evict = heap.poll();
                map.remove(evict.key);
            }
            map.put(item.key, item);
            heap.offer(item);
        }

        CacheItem get(K key) {
            CacheItem item = map.get(key);
            if (item != null) {
                item.freq++;
                item.timestamp = System.nanoTime();
                heap.remove(item);
                heap.offer(item);
            }
            return item;
        }

        void remove(K key) {
            CacheItem item = map.remove(key);
            if (item != null) heap.remove(item);
        }
    }

    private CacheLevel L1, L2, L3;
    private Map<K, CacheItem> allItems; 

    public MultiLevelCacheSystem() {
        L1 = new CacheLevel(2, 1);
        L2 = new CacheLevel(5, 3);
        L3 = new CacheLevel(10, 10);
        allItems = new HashMap<>();
    }

    public V get(K key) {
        CacheItem item = L1.get(key);
        if (item != null) return item.value;
        item = L2.get(key);
        if (item != null) {
            promote(item);
            return item.value;
        }
        item = L3.get(key);
        if (item != null) {
            promote(item);
            return item.value;
        }
        return null; 
    }

    public void put(K key, V value) {
        CacheItem item = allItems.get(key);
        if (item != null) {
            item.value = value;
            get(key);
        } else {
            item = new CacheItem(key, value);
            allItems.put(key, item);
            L3.put(item);
            promote(item);
        }
    }

    private void promote(CacheItem item) {
        CacheLevel[] levels = new CacheLevel[]{L1, L2, L3};
        for (int i = levels.length - 1; i >= 0; i--) {
            CacheLevel level = levels[i];
            if (level.map.containsKey(item.key)) {
                if (i > 0) {
                    CacheLevel upper = levels[i - 1];
                    double itemScore = (double)item.freq / upper.cost;
                    CacheItem lowest = upper.heap.peek();
                    double lowestScore = lowest != null ? (double)lowest.freq / upper.cost : -1;
                    if (itemScore > lowestScore) {
                        level.remove(item.key);
                        upper.put(item);
                    }
                }
                break;
            }
        }
    }

    public void printCache() {
        System.out.println("L1: " + L1.map.keySet());
        System.out.println("L2: " + L2.map.keySet());
        System.out.println("L3: " + L3.map.keySet());
        System.out.println("-----------");
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem<Integer, String> cache = new MultiLevelCacheSystem<>();

        cache.put(1,"A"); cache.put(2,"B"); cache.put(3,"C");
        cache.printCache(); 

        cache.get(1); cache.get(1); cache.get(2);
        cache.printCache(); 

        cache.put(4,"D"); cache.put(5,"E"); cache.put(6,"F");
        cache.printCache(); 
    }
}
