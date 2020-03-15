import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
    static final int MODULUS = 1_000_000_007;

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[] sortedIndices = IntStream.range(0, n).boxed().sorted((i1, i2) -> {
            return -Integer.compare(efficiency[i1], efficiency[i2]);
        }).mapToInt(x -> x).toArray();

        long result = Long.MIN_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long speedSum = 0;
        for (int sortedIndex : sortedIndices) {
            pq.offer(speed[sortedIndex]);
            speedSum += speed[sortedIndex];

            if (pq.size() == k + 1) {
                speedSum -= pq.poll();
            }

            result = Math.max(result, speedSum * efficiency[sortedIndex]);
        }

        return (int) (result % MODULUS);
    }
}