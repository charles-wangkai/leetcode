import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public long countGoodIntegers(int n, int k) {
    Set<String> keys = new HashSet<>();
    int halfMin = 1;
    for (int i = 0; i < (n + 1) / 2 - 1; ++i) {
      halfMin *= 10;
    }
    for (int half = halfMin; half < halfMin * 10; ++half) {
      String s = String.valueOf(half);
      long value = Long.parseLong(s + new StringBuilder(s).reverse().substring(n % 2));
      if (value % k == 0) {
        keys.add(
            String.valueOf(value)
                .chars()
                .sorted()
                .mapToObj(c -> (char) c)
                .map(String::valueOf)
                .collect(Collectors.joining()));
      }
    }

    return keys.stream()
        .mapToInt(
            key -> {
              int[] counts = new int[10];
              for (char c : key.toCharArray()) {
                ++counts[c - '0'];
              }

              int result = computeRearrangedNum(counts);
              if (counts[0] != 0) {
                --counts[0];
                result -= computeRearrangedNum(counts);
              }

              return result;
            })
        .asLongStream()
        .sum();
  }

  int computeRearrangedNum(int[] counts) {
    int result = 1;
    int rest = Arrays.stream(counts).sum();
    for (int count : counts) {
      result *= C(rest, count);
      rest -= count;
    }

    return result;
  }

  int C(int n, int r) {
    int result = 1;
    for (int i = 0; i < r; ++i) {
      result = result * (n - i) / (i + 1);
    }

    return result;
  }
}