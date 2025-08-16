import java.util.*;

public class MeetingRoomScheduler {

    public int minMeetingRooms(int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();

        for (int[] m : meetings) {
            if (!endTimes.isEmpty() && endTimes.peek() <= m[0]) {
                endTimes.poll();
            }
            endTimes.offer(m[1]);
        }
        return endTimes.size();
    }

    public int maxMeetingTimeWithLimit(int[][] meetings, int roomCount) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1])); // 按結束時間排
        int totalTime = 0;
        int[] roomEndTime = new int[roomCount];

        for (int[] m : meetings) {
            for (int r = 0; r < roomCount; r++) {
                if (roomEndTime[r] <= m[0]) {
                    totalTime += m[1] - m[0];
                    roomEndTime[r] = m[1];
                    break;
                }
            }
        }
        return totalTime;
    }

    public static void main(String[] args) {
        MeetingRoomScheduler mrs = new MeetingRoomScheduler();
        System.out.println(mrs.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); // 2
        System.out.println(mrs.minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}})); // 2
        System.out.println(mrs.minMeetingRooms(new int[][]{{1,5},{8,9},{8,9}})); // 2

        System.out.println(mrs.maxMeetingTimeWithLimit(new int[][]{{1,4},{2,3},{4,6}}, 1)); // 5
    }
}