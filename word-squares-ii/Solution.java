import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  public List<List<String>> wordSquares(String[] words) {
    List<List<String>> result = new ArrayList<>();
    for (int topIndex = 0; topIndex < words.length; ++topIndex) {
      for (int leftIndex = 0; leftIndex < words.length; ++leftIndex) {
        if (leftIndex != topIndex) {
          for (int rightIndex = 0; rightIndex < words.length; ++rightIndex) {
            if (rightIndex != topIndex && rightIndex != leftIndex) {
              for (int bottomIndex = 0; bottomIndex < words.length; ++bottomIndex) {
                if (bottomIndex != topIndex
                    && bottomIndex != leftIndex
                    && bottomIndex != rightIndex
                    && words[topIndex].charAt(0) == words[leftIndex].charAt(0)
                    && words[topIndex].charAt(3) == words[rightIndex].charAt(0)
                    && words[bottomIndex].charAt(0) == words[leftIndex].charAt(3)
                    && words[bottomIndex].charAt(3) == words[rightIndex].charAt(3)) {
                  result.add(
                      List.of(
                          words[topIndex],
                          words[leftIndex],
                          words[rightIndex],
                          words[bottomIndex]));
                }
              }
            }
          }
        }
      }
    }

    Collections.sort(result, Comparator.comparing(square -> String.join(",", square)));

    return result;
  }
}