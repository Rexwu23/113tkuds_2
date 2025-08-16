import java.util.*;

class CacheEntry implements Comparable<CacheEntry> {
    int key;
    String value;
    int frequency;
    long timestamp;
    int costLevel; // L1=1, L2=3, L3=10

    public CacheEntry(int key, String value, int costLevel) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
        this.timestamp = System.nanoTime();
        this.costLevel = costLevel;
    }

    @Override
    public int compareTo(CacheEntry other) {
        if (this.frequency != other.frequency)
            return Integer.compare(this.frequency, other.frequency);
        return Long.compare(this.timestamp, other.timestamp);
    }
}

public class MultiLevelCacheSystem {
    private int L1_CAP = 2, L2_CAP = 5, L3_CAP = 10;
    private Map<Integer, CacheEntry> L1 = new HashMap<>();
    private Map<Integer, CacheEntry> L2 = new HashMap<>();
    private Map<Integer, CacheEntry> L3 = new HashMap<>();
    private PriorityQueue<CacheEntry> pqL1 = new PriorityQueue<>();
    private PriorityQueue<CacheEntry> pqL2 = new PriorityQueue<>();
    private PriorityQueue<CacheEntry> pqL3 = new PriorityQueue<>();

    public void put(int key, String value) {
        if (L1.containsKey(key) || L2.containsKey(key) || L3.containsKey(key)) {
            get(key);
            return;
        }
        CacheEntry entry = new CacheEntry(key, value, 10);
        addToL3(entry);
    }

    public String get(int key) {
        if (L1.containsKey(key)) {
            CacheEntry e = L1.get(key);
            e.frequency++;
            e.timestamp = System.nanoTime();
            return e.value;
        }
        if (L2.containsKey(key)) {
            CacheEntry e = L2.remove(key);
            pqL2.remove(e);
            e.frequency++;
            addToL1(e);
            return e.value;
        }
        if (L3.containsKey(key)) {
            CacheEntry e = L3.remove(key);
            pqL3.remove(e);
            e.frequency++;
            addToL2(e);
            return e.value;
        }
        return null;
    }

    private void addToL1(CacheEntry e) {
        e.costLevel = 1;
        if (L1.size() >= L1_CAP) {
            CacheEntry evict = pqL1.poll();
            L1.remove(evict.key);
            addToL2(evict);
        }
        L1.put(e.key, e);
        pqL1.offer(e);
    }

    private void addToL2(CacheEntry e) {
        e.costLevel = 3;
        if (L2.size() >= L2_CAP) {
            CacheEntry evict = pqL2.poll();
            L2.remove(evict.key);
            addToL3(evict);
        }
        L2.put(e.key, e);
        pqL2.offer(e);
    }

    private void addToL3(CacheEntry e) {
        e.costLevel = 10;
        if (L3.size() >= L3_CAP) {
            CacheEntry evict = pqL3.poll();
            L3.remove(evict.key);
        }
        L3.put(e.key, e);
        pqL3.offer(e);
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        System.out.println(cache.L1.keySet()); // [2, 3]
        System.out.println(cache.L2.keySet()); // [1]
        System.out.println(cache.L3.keySet()); // []

        cache.get(1);
        cache.get(1);
        cache.get(2);

        System.out.println(cache.L1.keySet()); // [1, 2]
        System.out.println(cache.L2.keySet()); // [3]
        System.out.println(cache.L3.keySet()); // []
    }
}