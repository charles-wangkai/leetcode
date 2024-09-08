import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public String convertDateToBinary(String date) {
    return Arrays.stream(date.split("-"))
        .mapToInt(Integer::parseInt)
        .mapToObj(Integer::toBinaryString)
        .collect(Collectors.joining("-"));
  }
}