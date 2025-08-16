import java.util.*;

public class MovingAverageStream {
    private Queue<Integer> window;
    private int size;
    private double sum;
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        addHeap(val);

        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            removeHeap(removed);
        }

        return sum / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        }
        return maxHeap.peek();
    }

    public int getMin() {
        return Collections.min(window);
    }

    public int getMax() {
        return Collections.max(window);
    }

    private void addHeap(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) maxHeap.offer(num);
        else minHeap.offer(num);
        balance();
    }

    private void removeHeap(int num) {
        if (num <= maxHeap.peek()) maxHeap.remove(num);
        else minHeap.remove(num);
        balance();
    }

    private void balance() {
        if (maxHeap.size() > minHeap.size() + 1) minHeap.offer(maxHeap.poll());
        else if (minHeap.size() > maxHeap.size()) maxHeap.offer(minHeap.poll());
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1)); // 1.0
        System.out.println(ma.next(10)); // 5.5
        System.out.println(ma.next(3)); // 4.67
        System.out.println(ma.next(5)); // 6.0
        System.out.println("Median: " + ma.getMedian()); // 5.0
        System.out.println("Min: " + ma.getMin()); // 3
        System.out.println("Max: " + ma.getMax()); // 10
    }
}