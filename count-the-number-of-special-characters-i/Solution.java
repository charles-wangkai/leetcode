import java.util.stream.IntStream;

class Solution {
  public int numberOfSpecialChars(String word) {
    return (int)
        IntStream.rangeClosed('a', 'z')
            .filter(c -> word.indexOf(c) != -1 && word.indexOf(Character.toUpperCase(c)) != -1)
            .count();
  }
}