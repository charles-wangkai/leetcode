import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public long specialPalindrome(long n) {
    SortedMap<Integer, List<Integer>> lengthToMasks = new TreeMap<>();
    for (int mask = 1; mask < 1 << 9; ++mask) {
      int length = getLength(mask);
      int mask_ = mask;
      if (length % 2
          == IntStream.range(0, 9).filter(i -> i % 2 == 0).map(i -> (mask_ >> i) & 1).sum()) {
        lengthToMasks.putIfAbsent(length, new ArrayList<>());
        lengthToMasks.get(length).add(mask);
      }
    }

    int nLength = String.valueOf(n).length();

    Iterator<Integer> iter = lengthToMasks.keySet().iterator();
    while (true) {
      int length = iter.next();
      if (length == nLength) {
        long solution =
            lengthToMasks.get(length).stream()
                .mapToLong(mask -> findSolution(n, mask))
                .min()
                .getAsLong();
        if (solution != Long.MAX_VALUE) {
          return solution;
        }
      } else if (length > nLength) {
        return lengthToMasks.get(length).stream()
            .mapToLong(mask -> buildMinValue(mask))
            .min()
            .getAsLong();
      }
    }
  }

  long findSolution(long n, int mask) {
    int[] digits = new int[getLength(mask)];
    if (digits.length % 2 == 1) {
      digits[digits.length / 2] =
          IntStream.range(0, 9)
                  .filter(i -> i % 2 == 0)
                  .filter(i -> ((mask >> i) & 1) == 1)
                  .findAny()
                  .getAsInt()
              + 1;
    }

    int[] rests =
        IntStream.range(0, 9).map(i -> (((mask >> i) & 1) == 1) ? ((i + 1) / 2) : 0).toArray();

    return search(n, rests, digits, digits.length / 2 - 1);
  }

  long search(long n, int[] rests, int[] digits, int leftIndex) {
    if (leftIndex == -1) {
      long value =
          Long.parseLong(
              Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining()));

      return (value > n) ? value : Long.MAX_VALUE;
    }

    long result = Long.MAX_VALUE;
    for (int i = 0; i < rests.length; ++i) {
      if (rests[i] != 0) {
        --rests[i];

        digits[leftIndex] = i + 1;
        digits[digits.length - 1 - leftIndex] = i + 1;
        result = Math.min(result, search(n, rests, digits, leftIndex - 1));

        ++rests[i];
      }
    }

    return result;
  }

  long buildMinValue(int mask) {
    int[] digits = new int[getLength(mask)];
    if (digits.length % 2 == 1) {
      digits[digits.length / 2] =
          IntStream.range(0, 9)
                  .filter(i -> i % 2 == 0)
                  .filter(i -> ((mask >> i) & 1) == 1)
                  .findAny()
                  .getAsInt()
              + 1;
    }

    int leftIndex = 0;
    int rightIndex = digits.length - 1;
    for (int i = 0; i < 9; ++i) {
      if (((mask >> i) & 1) == 1) {
        for (int j = 0; j < (i + 1) / 2; ++j) {
          digits[leftIndex] = i + 1;
          ++leftIndex;

          digits[rightIndex] = i + 1;
          --rightIndex;
        }
      }
    }

    return Long.parseLong(
        Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining()));
  }

  int getLength(int mask) {
    return IntStream.range(0, 9).filter(i -> ((mask >> i) & 1) == 1).map(i -> i + 1).sum();
  }
}