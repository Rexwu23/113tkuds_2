import java.util.*;

class ArrayEntry implements Comparable<ArrayEntry> {
    int value;
    int arrayIndex;
    int elementIndex;

    public ArrayEntry(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }

    @Override
    public int compareTo(ArrayEntry other) {
        return Integer.compare(this.value, other.value);
    }
}

public class MergeKSortedArrays {

    public static List<Integer> merge(int[][] arrays) {
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new ArrayEntry(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            ArrayEntry entry = minHeap.poll();
            result.add(entry.value);

            if (entry.elementIndex + 1 < arrays[entry.arrayIndex].length) {
                minHeap.offer(new ArrayEntry(
                    arrays[entry.arrayIndex][entry.elementIndex + 1],
                    entry.arrayIndex,
                    entry.elementIndex + 1
                ));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] input = {{1,4,5}, {1,3,4}, {2,6}};
        System.out.println(merge(input)); // [1, 1, 2, 3, 4, 4, 5, 6]
    }
}