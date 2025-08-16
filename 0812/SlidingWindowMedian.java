import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 小的一半
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 大的一半

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new double[0];
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
            if (i >= k) remove(nums[i - k]); // 移出窗口外元素
            if (i >= k - 1) result[i - k + 1] = getMedian();
        }
        return result;
    }

    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) maxHeap.offer(num);
        else minHeap.offer(num);
        balance();
    }

    private void remove(int num) {
        if (num <= maxHeap.peek()) maxHeap.remove(num);
        else minHeap.remove(num);
        balance();
    }

    private void balance() {
        if (maxHeap.size() > minHeap.size() + 1) minHeap.offer(maxHeap.poll());
        else if (minHeap.size() > maxHeap.size()) maxHeap.offer(minHeap.poll());
    }

    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] arr1 = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(arr1, 3))); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] arr2 = {1,2,3,4};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(arr2, 2))); // [1.5, 2.5, 3.5]
    }
}