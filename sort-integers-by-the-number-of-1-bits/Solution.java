import java.util.Arrays;

public class Solution {
    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr).boxed()
                .sorted((x, y) -> (Integer.bitCount(x) != Integer.bitCount(y))
                        ? Integer.compare(Integer.bitCount(x), Integer.bitCount(y))
                        : Integer.compare(x, y))
                .mapToInt(x -> x).toArray();
    }
}