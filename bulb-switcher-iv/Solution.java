import java.util.stream.IntStream;

class Solution {
    public int minFlips(String target) {
        return (int) IntStream.range(0, target.length())
                .filter(i -> target.charAt(i) != ((i == 0) ? '0' : target.charAt(i - 1))).count();
    }
}