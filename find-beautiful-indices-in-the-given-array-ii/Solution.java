import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
  static final int BASE_1 = 31;
  static final int BASE_2 = 37;

  public List<Integer> beautifulIndices(String s, String a, String b, int k) {
    NavigableSet<Integer> bIndices = new TreeSet<>(findIndices(s, b));

    return findIndices(s, a).stream()
        .filter(
            i -> {
              Integer lower = bIndices.lower(i);
              if (lower != null && i - lower <= k) {
                return true;
              }

              Integer ceiling = bIndices.ceiling(i);

              return ceiling != null && ceiling - i <= k;
            })
        .toList();
  }

  List<Integer> findIndices(String s, String target) {
    if (s.length() < target.length()) {
      return List.of();
    }

    long targetHash1 = 0;
    long targetHash2 = 0;
    for (char c : target.toCharArray()) {
      targetHash1 = targetHash1 * BASE_1 + (c - 'a');
      targetHash2 = targetHash2 * BASE_2 + (c - 'a');
    }

    long maxPlaceValue1 = 1;
    long maxPlaceValue2 = 1;
    for (int i = 0; i < target.length() - 1; ++i) {
      maxPlaceValue1 *= BASE_1;
      maxPlaceValue2 *= BASE_2;
    }

    long hash1 = 0;
    long hash2 = 0;
    for (int i = 0; i < target.length() - 1; ++i) {
      hash1 = hash1 * BASE_1 + (s.charAt(i) - 'a');
      hash2 = hash2 * BASE_2 + (s.charAt(i) - 'a');
    }

    List<Integer> result = new ArrayList<>();
    for (int i = target.length() - 1; i < s.length(); ++i) {
      hash1 = hash1 * BASE_1 + (s.charAt(i) - 'a');
      hash2 = hash2 * BASE_2 + (s.charAt(i) - 'a');

      if (hash1 == targetHash1 && hash2 == targetHash2) {
        result.add(i - target.length() + 1);
      }

      hash1 -= maxPlaceValue1 * (s.charAt(i - target.length() + 1) - 'a');
      hash2 -= maxPlaceValue2 * (s.charAt(i - target.length() + 1) - 'a');
    }

    return result;
  }
}