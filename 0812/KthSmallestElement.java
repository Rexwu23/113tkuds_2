import java.util.*;

public class KthSmallestElement {

    // 方法 1: Max Heap (大小為 K)
    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) maxHeap.poll();
        }
        return maxHeap.peek();
    }

    // 方法 2: Min Heap (取 K 次)
    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) minHeap.offer(num);
        int val = -1;
        for (int i = 0; i < k; i++) val = minHeap.poll();
        return val;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        System.out.println(kthSmallestMaxHeap(arr1, 3)); // 7
        System.out.println(kthSmallestMinHeap(arr1, 3)); // 7
    }
}