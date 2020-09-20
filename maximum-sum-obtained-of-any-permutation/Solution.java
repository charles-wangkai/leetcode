import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    static final int MODULUS = 1_000_000_007;

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int[] deltas = new int[nums.length];
        for (int[] request : requests) {
            ++deltas[request[0]];
            if (request[1] != nums.length - 1) {
                --deltas[request[1] + 1];
            }
        }

        int[] counts = new int[nums.length];
        int count = 0;
        for (int i = 0; i < counts.length; ++i) {
            count += deltas[i];
            counts[i] = count;
        }

        int[] sortedCounts = Arrays.stream(counts).boxed().sorted().mapToInt(x -> x).toArray();
        int[] sortedNums = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

        return IntStream.range(0, nums.length).map(i -> multiplyMod(sortedNums[i], sortedCounts[i]))
                .reduce(this::addMod).getAsInt();
    }

    int addMod(int x, int y) {
        return (x + y) % MODULUS;
    }

    int multiplyMod(int x, int y) {
        return (int) ((long) x * y % MODULUS);
    }
}