import java.util.stream.IntStream;

class Solution {
  public String maximumBinaryString(String binary) {
    int leadingOneCount = 0;
    while (leadingOneCount != binary.length() && binary.charAt(leadingOneCount) == '1') {
      ++leadingOneCount;
    }

    StringBuilder result = new StringBuilder("1".repeat(binary.length()));
    int restOneCount =
        (int)
            IntStream.range(leadingOneCount, binary.length())
                .filter(i -> binary.charAt(i) == '1')
                .count();
    if (leadingOneCount != binary.length()) {
      result.setCharAt(binary.length() - 1 - restOneCount, '0');
    }

    return result.toString();
  }
}
