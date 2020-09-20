import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public String reorderSpaces(String text) {
        int spaceNum = (int) text.chars().filter(ch -> ch == ' ').count();
        String[] words = Arrays.stream(text.split(" ")).filter(word -> !word.isEmpty()).toArray(String[]::new);

        String result;
        if (words.length == 1) {
            result = words[0];
        } else {
            String separator = repeat(' ', spaceNum / (words.length - 1));
            result = String.join(separator, words);
        }

        result += repeat(' ', text.length() - result.length());

        return result;
    }

    String repeat(char ch, int count) {
        return IntStream.range(0, count).mapToObj(i -> String.valueOf(ch)).collect(Collectors.joining());
    }
}