import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public String reformatNumber(String number) {
    String digits =
        number
            .chars()
            .filter(Character::isDigit)
            .mapToObj(ch -> String.valueOf((char) ch))
            .collect(Collectors.joining());

    int index = 0;
    List<String> blocks = new ArrayList<>();
    while (index != digits.length()) {
      int restLength = digits.length() - index;
      int blockLength;
      if (restLength == 2 || restLength == 4) {
        blockLength = 2;
      } else {
        blockLength = 3;
      }

      blocks.add(digits.substring(index, index + blockLength));
      index += blockLength;
    }

    return String.join("-", blocks);
  }
}
