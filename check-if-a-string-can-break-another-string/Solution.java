import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        String sorted1 = sort(s1);
        String sorted2 = sort(s2);

        return IntStream.range(0, sorted1.length()).allMatch(i -> sorted1.charAt(i) <= sorted2.charAt(i))
                || IntStream.range(0, sorted1.length()).allMatch(i -> sorted1.charAt(i) >= sorted2.charAt(i));
    }

    String sort(String s) {
        return s.chars().sorted().mapToObj(ch -> String.valueOf((char) ch)).collect(Collectors.joining());
    }
}