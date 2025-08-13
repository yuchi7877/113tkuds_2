import java.util.*;

public class MeetingRoomScheduler {
    public static int minMeetingRooms(int[][] meetings) {
        if (meetings.length == 0) return 0;

        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(meetings[0][1]);

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(meetings[i][1]);
        }
        return minHeap.size();
    }

    public static int maxMeetingTimeWithRooms(int[][] meetings, int N) {
        if (meetings.length == 0 || N == 0) return 0;

        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        int[][] dp = new int[meetings.length + 1][N + 1];
        int[] prev = new int[meetings.length];
        for (int i = 0; i < meetings.length; i++) {
            prev[i] = -1;
            for (int k = i - 1; k >= 0; k--) {
                if (meetings[k][1] <= meetings[i][0]) {
                    prev[i] = k;
                    break;
                }
            }
        }

        for (int i = 1; i <= meetings.length; i++) {
            int duration = meetings[i - 1][1] - meetings[i - 1][0];
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]){
                if (prev[i - 1] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[prev[i - 1] + 1][j] + duration);
                } else {
                    dp[i][j] = Math.max(dp[i][j], duration);
                }
            }
        }
        return dp[meetings.length][N];
    }

    public static void main(String[] args) {
        int[][] m1 = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(m1)); 

        int[][] m2 = {{9,10},{4,9},{4,17}};
        System.out.println(minMeetingRooms(m2)); 

        int[][] m3 = {{1,5},{8,9},{8,9}};
        System.out.println(minMeetingRooms(m3)); 

        int[][] m4 = {{1,4},{2,3},{4,6}};
        System.out.println(maxMeetingTimeWithRooms(m4, 1)); 
    }
}
