import java.util.stream.Collectors;

class Solution {
  static final int[] BASES = {2, 3, 5, 7};

  public String smallestNumber(long n) {
    if (n == 1) {
      return "1";
    }

    String digits = "";
    for (int base : BASES) {
      int exponent = 0;
      while (n % base == 0) {
        ++exponent;
        n /= base;
      }

      digits += String.valueOf(base).repeat(exponent);
    }

    if (n != 1) {
      return "-1";
    }

    return sort(
        sort(sort(digits).replace("222", "8").replace("33", "9"))
            .replace("23", "6")
            .replace("22", "4"));
  }

  String sort(String digits) {
    return digits
        .chars()
        .sorted()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
