import java.util.*;

class Task implements Comparable<Task> {
    String name;
    int priority;
    long timestamp;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.timestamp = System.nanoTime();
    }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority)
            return Integer.compare(other.priority, this.priority);
        return Long.compare(this.timestamp, other.timestamp);
    }

    @Override
    public String toString() {
        return name + "(P=" + priority + ")";
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> pq;

    public PriorityQueueWithHeap() {
        pq = new PriorityQueue<>();
    }

    public void addTask(String name, int priority) {
        pq.offer(new Task(name, priority));
    }

    public Task executeNext() {
        return pq.poll();
    }

    public Task peek() {
        return pq.peek();
    }

    public void changePriority(String name, int newPriority) {
        List<Task> temp = new ArrayList<>();
        while (!pq.isEmpty()) {
            Task t = pq.poll();
            if (t.name.equals(name)) {
                t.priority = newPriority;
                t.timestamp = System.nanoTime();
            }
            temp.add(t);
        }
        pq.addAll(temp);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap queue = new PriorityQueueWithHeap();
        queue.addTask("備份", 1);
        queue.addTask("緊急修復", 5);
        queue.addTask("更新", 3);

        System.out.println(queue.executeNext()); // 緊急修復
        System.out.println(queue.executeNext()); // 更新
        System.out.println(queue.executeNext()); // 備份
    }
}