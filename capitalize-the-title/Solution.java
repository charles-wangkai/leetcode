import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public String capitalizeTitle(String title) {
    return Arrays.stream(title.split(" "))
        .map(
            word ->
                (word.length() <= 2)
                    ? word.toLowerCase()
                    : String.format(
                        "%c%s",
                        Character.toUpperCase(word.charAt(0)), word.substring(1).toLowerCase()))
        .collect(Collectors.joining(" "));
  }
}