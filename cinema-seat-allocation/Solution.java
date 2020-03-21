import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> rowToColumns = new HashMap<>();
        for (int[] seat : reservedSeats) {
            if (!rowToColumns.containsKey(seat[0])) {
                rowToColumns.put(seat[0], new HashSet<>());
            }

            rowToColumns.get(seat[0]).add(seat[1]);
        }

        return (n - rowToColumns.size()) * 2 + rowToColumns.values().stream().mapToInt(columns -> {
            if (isAvailable(columns, 2, 9)) {
                return 2;
            } else if (isAvailable(columns, 2, 5) || isAvailable(columns, 6, 9) || isAvailable(columns, 4, 7)) {
                return 1;
            } else {
                return 0;
            }
        }).sum();
    }

    boolean isAvailable(Set<Integer> columns, int begin, int end) {
        return IntStream.rangeClosed(begin, end).allMatch(x -> !columns.contains(x));
    }
}