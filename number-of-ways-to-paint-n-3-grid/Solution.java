import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    static final char[] COLORS = { 'R', 'Y', 'G' };
    static final int MODULUS = 1_000_000_007;

    public int numOfWays(int n) {
        List<String> rows = new ArrayList<>();
        for (char c1 : COLORS) {
            for (char c2 : COLORS) {
                if (c2 != c1) {
                    for (char c3 : COLORS) {
                        if (c3 != c2) {
                            rows.add(String.format("%c%c%c", c1, c2, c3));
                        }
                    }
                }
            }
        }

        Map<String, Integer> lastRowToWayNum = rows.stream().collect(Collectors.toMap(Function.identity(), x -> 1));
        for (int i = 0; i < n - 1; ++i) {
            Map<String, Integer> nextLastRowToWayNum = new HashMap<>();
            for (String row : rows) {
                int wayNum = 0;
                for (String lastRow : rows) {
                    if (check(lastRow, row)) {
                        wayNum = addMod(wayNum, lastRowToWayNum.get(lastRow));
                    }
                }

                nextLastRowToWayNum.put(row, wayNum);
            }

            lastRowToWayNum = nextLastRowToWayNum;
        }

        return lastRowToWayNum.values().stream().mapToInt(x -> x).reduce(this::addMod).getAsInt();
    }

    boolean check(String lastRow, String row) {
        return IntStream.range(0, lastRow.length()).allMatch(i -> lastRow.charAt(i) != row.charAt(i));
    }

    int addMod(int x, int y) {
        return (x + y) % MODULUS;
    }
}