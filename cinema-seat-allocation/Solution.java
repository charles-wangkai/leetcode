import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
    Map<Integer, Set<Integer>> rowToReserved = new HashMap<>();
    for (int[] seat : reservedSeats) {
      rowToReserved.putIfAbsent(seat[0], new HashSet<>());
      rowToReserved.get(seat[0]).add(seat[1]);
    }

    return (n - rowToReserved.size()) * 2
        + rowToReserved.values().stream()
            .mapToInt(
                columns -> {
                  if (isAvailable(columns, 2, 9)) {
                    return 2;
                  }
                  if (isAvailable(columns, 2, 5)
                      || isAvailable(columns, 4, 7)
                      || isAvailable(columns, 6, 9)) {
                    return 1;
                  }

                  return 0;
                })
            .sum();
  }

  boolean isAvailable(Set<Integer> reserved, int begin, int end) {
    return IntStream.rangeClosed(begin, end).allMatch(x -> !reserved.contains(x));
  }
}