import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);

        return IntStream.range(1, arr.length - 1).allMatch(i -> arr[i + 1] - arr[i] == arr[1] - arr[0]);
    }
}