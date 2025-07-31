import java.util.HashSet;
import java.util.Set;

class Solution {
  public int subarrayBitwiseORs(int[] arr) {
    Set<Integer> allOrs = new HashSet<>();
    Set<Integer> prevOrs = Set.of();
    for (int value : arr) {
      Set<Integer> currOrs = new HashSet<>();
      currOrs.add(value);
      for (int prevOr : prevOrs) {
        currOrs.add(prevOr | value);
      }

      allOrs.addAll(currOrs);
      prevOrs = currOrs;
    }

    return allOrs.size();
  }
}
