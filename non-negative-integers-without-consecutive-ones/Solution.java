import java.util.HashMap;
import java.util.Map;

class Solution {
  Map<String, Integer> cache = new HashMap<>();

  public int findIntegers(int n) {
    return search(Integer.toBinaryString(n));
  }

  int search(String s) {
    if (s.isEmpty()) {
      return 1;
    }
    if (s.length() == 1) {
      return Integer.parseInt(s) + 1;
    }
    if (s.charAt(0) == '0') {
      return search(s.substring(1));
    }

    if (!cache.containsKey(s)) {
      cache.put(
          s,
          search("1".repeat(s.length() - 1))
              + search((s.charAt(1) == '0') ? s.substring(2) : "1".repeat(s.length() - 2)));
    }

    return cache.get(s);
  }
}
