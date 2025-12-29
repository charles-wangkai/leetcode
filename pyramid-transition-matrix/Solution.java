import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  public boolean pyramidTransition(String bottom, List<String> allowed) {
    Map<String, List<Character>> baseToTops = new HashMap<>();
    for (String a : allowed) {
      String base = a.substring(0, 2);
      char top = a.charAt(2);

      baseToTops.putIfAbsent(base, new ArrayList<>());
      baseToTops.get(base).add(top);
    }

    return search(baseToTops, new HashSet<>(), bottom, new StringBuilder());
  }

  boolean search(
      Map<String, List<Character>> baseToTops,
      Set<String> visitedRows,
      String row,
      StringBuilder nextRow) {
    if (row.length() == 1) {
      return true;
    }

    if (nextRow.length() == row.length() - 1) {
      return search(baseToTops, visitedRows, nextRow.toString(), new StringBuilder());
    }

    if (nextRow.isEmpty() && visitedRows.contains(row)) {
      return false;
    }

    String base = row.substring(nextRow.length(), nextRow.length() + 2);
    if (baseToTops.containsKey(base)) {
      for (char top : baseToTops.get(base)) {
        nextRow.append(top);

        if (search(baseToTops, visitedRows, row, nextRow)) {
          return true;
        }

        nextRow.deleteCharAt(nextRow.length() - 1);
      }
    }

    if (nextRow.isEmpty()) {
      visitedRows.add(row);
    }

    return false;
  }
}
