import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
  public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
    String[] replaced = new String[S.length()];
    Arrays.fill(replaced, "");

    int currentIndex = 0;
    while (currentIndex < replaced.length) {
      int index = Arrays.stream(indexes).boxed().toList().indexOf(currentIndex);
      if (index >= 0 && S.startsWith(sources[index], currentIndex)) {
        replaced[currentIndex] = targets[index];

        currentIndex += sources[index].length();
      } else {
        replaced[currentIndex] = String.valueOf(S.charAt(currentIndex));

        currentIndex++;
      }
    }

    return Arrays.stream(replaced).collect(Collectors.joining(""));
  }
}
