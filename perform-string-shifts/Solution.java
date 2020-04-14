import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public String stringShift(String s, int[][] shift) {
        int delta = 0;
        for (int[] sh : shift) {
            delta += (sh[0] == 1 ? 1 : -1) * sh[1];
        }

        int delta_ = delta;
        return IntStream.range(0, s.length())
                .mapToObj(i -> String.valueOf(s.charAt(((i - delta_) % s.length() + s.length()) % s.length())))
                .collect(Collectors.joining());
    }
}