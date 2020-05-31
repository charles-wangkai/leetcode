import java.util.stream.IntStream;

class Solution {
    public boolean hasAllCodes(String s, int k) {
        return IntStream.range(0, s.length() - k + 1).mapToObj(i -> s.substring(i, i + k)).distinct().count() == 1 << k;
    }
}