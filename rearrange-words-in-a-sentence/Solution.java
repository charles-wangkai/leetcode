import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public String arrangeWords(String text) {
        String[] words = text.toLowerCase().split(" ");
        int[] sortedIndices = IntStream.range(0, words.length).boxed()
                .sorted((i1, i2) -> (words[i1].length() != words[i2].length())
                        ? Integer.compare(words[i1].length(), words[i2].length())
                        : Integer.compare(i1, i2))
                .mapToInt(x -> x).toArray();

        return IntStream.range(0, sortedIndices.length)
                .mapToObj(i -> (i == 0) ? toTitle(words[sortedIndices[i]]) : words[sortedIndices[i]])
                .collect(Collectors.joining(" "));
    }

    static String toTitle(String s) {
        return String.format("%c%s", Character.toUpperCase(s.charAt(0)), s.substring(1));
    }
}