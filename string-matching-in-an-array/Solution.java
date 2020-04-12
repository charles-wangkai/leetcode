import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public List<String> stringMatching(String[] words) {
        return Arrays.stream(words)
                .filter(word -> Arrays.stream(words).anyMatch(w -> !w.equals(word) && w.contains(word)))
                .collect(Collectors.toList());
    }
}