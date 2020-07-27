import java.util.stream.IntStream;

class Solution {
    public int minNumberOperations(int[] target) {
        return IntStream.range(0, target.length)
                .map(i -> Math.max(0, target[i] - ((i == target.length - 1) ? 0 : target[i + 1]))).sum();
    }
}