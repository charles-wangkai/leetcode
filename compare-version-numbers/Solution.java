import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int compareVersion(String version1, String version2) {
    int[] numbers1 = convertToNumbers(version1);
    int[] numbers2 = convertToNumbers(version2);

    return IntStream.range(0, Math.max(numbers1.length, numbers2.length))
        .map(i -> Integer.compare(getNumber(numbers1, i), getNumber(numbers2, i)))
        .filter(x -> x != 0)
        .findFirst()
        .orElse(0);
  }

  int[] convertToNumbers(String version) {
    return Arrays.stream(version.split("\\.")).mapToInt(Integer::parseInt).toArray();
  }

  int getNumber(int[] numbers, int index) {
    return (index < numbers.length) ? numbers[index] : 0;
  }
}
