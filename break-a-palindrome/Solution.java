import java.util.OptionalInt;
import java.util.stream.IntStream;

class Solution {
  public String breakPalindrome(String palindrome) {
    if (palindrome.length() == 1) {
      return "";
    }

    OptionalInt index =
        IntStream.range(0, palindrome.length())
            .filter(i -> i != palindrome.length() - 1 - i && palindrome.charAt(i) != 'a')
            .findFirst();
    if (index.isPresent()) {
      return String.format(
          "%sa%s",
          palindrome.substring(0, index.getAsInt()), palindrome.substring(index.getAsInt() + 1));
    }

    return String.format("%sb", palindrome.substring(0, palindrome.length() - 1));
  }
}
