import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        return IntStream.concat(Arrays.stream(left), Arrays.stream(right).map(r -> n - r)).max().getAsInt();
    }
}