import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    public boolean isPossible(int[] target) {
        int oneCount = 0;
        long pqSum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int x : target) {
            if (x == 1) {
                ++oneCount;
            } else {
                pq.offer(x);
                pqSum += x;
            }
        }

        while (!pq.isEmpty()) {
            int max = pq.poll();
            pqSum -= max;

            long prev = max - pqSum - oneCount;
            if (prev < 1) {
                return false;
            } else if (prev == 1) {
                ++oneCount;
            } else {
                pq.offer((int) prev);
                pqSum += prev;
            }
        }

        return true;
    }
}