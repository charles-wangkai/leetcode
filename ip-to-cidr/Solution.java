import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public List<String> ipToCIDR(String ip, int range) {
    long startNumber = convertToNumber(ip);
    long endNumber = startNumber + range - 1;

    List<String> result = new ArrayList<>();
    while (startNumber <= endNumber) {
      int prefixLength = 0;
      while (prefixLength < 32 && startNumber % (1L << (32 - prefixLength)) != 0) {
        prefixLength++;
      }

      while (prefixLength < 32 && startNumber + (1 << (32 - prefixLength)) - 1 > endNumber) {
        prefixLength++;
      }
      result.add(convertToIp(startNumber) + "/" + prefixLength);

      startNumber += 1 << (32 - prefixLength);
    }
    return result;
  }

  String convertToIp(long number) {
    return String.join(
        ".",
        IntStream.range(0, 4)
            .mapToObj(i -> String.valueOf(number / (1 << 8 * (3 - i)) % 256))
            .collect(Collectors.toList()));
  }

  long convertToNumber(String ip) {
    long number = 0;
    for (String part : ip.split(Pattern.quote("."))) {
      number = number * 256 + Integer.parseInt(part);
    }
    return number;
  }
}
