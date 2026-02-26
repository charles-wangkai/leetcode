import java.util.stream.IntStream;

class Solution {
  public int numSteps(String s) {
    int result = 0;
    int index = s.length() - 1;
    while (s.charAt(index) == '0') {
      ++result;
      --index;
    }

    if (index != 0) {
      result += index + IntStream.range(0, index).filter(i -> s.charAt(i) == '0').count() + 2;
    }

    return result;
  }
}