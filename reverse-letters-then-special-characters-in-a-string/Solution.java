import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

class Solution {
  public String reverseByType(String s) {
    return reverse(reverse(s, Character::isLetter), c -> !Character.isLetter(c));
  }

  String reverse(String str, Predicate<Character> predicate) {
    int[] indices =
        IntStream.range(0, str.length()).filter(i -> predicate.test(str.charAt(i))).toArray();
    Character[] chs = Arrays.stream(indices).mapToObj(str::charAt).toArray(Character[]::new);
    for (int i = 0, j = chs.length - 1; i < j; ++i, --j) {
      char temp = chs[i];
      chs[i] = chs[j];
      chs[j] = temp;
    }

    char[] result = str.toCharArray();
    for (int i = 0; i < indices.length; ++i) {
      result[indices[i]] = chs[i];
    }

    return String.valueOf(result);
  }
}