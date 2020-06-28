import java.util.PriorityQueue;

class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int result = Integer.MIN_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1[0] - p1[1], p2[0] - p2[1]));
        for (int i = 0; i < points.length; ++i) {
            int[] current = points[i];

            while (!pq.isEmpty() && current[0] - pq.peek()[0] > k) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                result = Math.max(result, current[0] + current[1] - (pq.peek()[0] - pq.peek()[1]));
            }

            pq.offer(current);
        }

        return result;
    }
}