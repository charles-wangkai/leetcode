import java.util.stream.IntStream;

class Solution {
    public int xorOperation(int n, int start) {
        return IntStream.range(0, n).map(i -> start + 2 * i).reduce((x, y) -> x ^ y).getAsInt();
    }
}