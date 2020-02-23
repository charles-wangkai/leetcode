import java.util.stream.IntStream;

public class Solution {
    public int countOrders(int n) {
        return IntStream.rangeClosed(1, n).map(i -> i * (2 * i - 1))
                .reduce((x, y) -> (int) ((long) x * y % 1_000_000_007)).getAsInt();
    }
}