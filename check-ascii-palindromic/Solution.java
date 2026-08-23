import java.util.stream.Collectors;

class Solution {
  public boolean isPalindromic(String s) {
    return isPalindrome(
        s.chars()
            .mapToObj(c -> toBinaryString((char) c))
            .map(String::valueOf)
            .collect(Collectors.joining()));
  }

  String toBinaryString(char letter) {
    String s = Integer.toBinaryString(letter);

    return "0".repeat(8 - s.length()) + s;
  }

  boolean isPalindrome(String str) {
    return new StringBuilder(str).reverse().toString().equals(str);
  }
}