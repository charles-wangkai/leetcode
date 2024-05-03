import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int compareVersion(String version1, String version2) {
    int[] values1 = convertToValues(version1);
    int[] values2 = convertToValues(version2);

    return IntStream.range(0, Math.max(values1.length, values2.length))
        .map(i -> Integer.compare(getValue(values1, i), getValue(values2, i)))
        .filter(x -> x != 0)
        .findFirst()
        .orElse(0);
  }

  int[] convertToValues(String version) {
    return Arrays.stream(version.split("\\.")).mapToInt(Integer::parseInt).toArray();
  }

  int getValue(int[] values, int index) {
    return (index < values.length) ? values[index] : 0;
  }
}
