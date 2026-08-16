import java.util.stream.IntStream;

class Solution {
  public int minOperations(String s) {
    return IntStream.range(0, s.length())
        .map(
            rotateNum ->
                rotateNum + computeIncrementNum(s.substring(rotateNum) + s.substring(0, rotateNum)))
        .min()
        .getAsInt();
  }

  int computeIncrementNum(String str) {
    int result = 0;
    for (int i = 0, j = str.length() - 1; i < j; ++i, --j) {
      int diff = Math.abs(str.charAt(i) - str.charAt(j));
      result += Math.min(diff, 26 - diff);
    }

    return result;
  }
}