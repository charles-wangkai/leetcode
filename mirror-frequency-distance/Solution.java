import java.util.HashMap;
import java.util.Map;

class Solution {
  public int mirrorFrequency(String s) {
    Map<Character, Integer> symbolToCount = new HashMap<>();
    for (char symbol : s.toCharArray()) {
      symbolToCount.put(symbol, symbolToCount.getOrDefault(symbol, 0) + 1);
    }

    int result = 0;
    for (char left = 'a', right = 'z'; left < right; ++left, --right) {
      result +=
          Math.abs(symbolToCount.getOrDefault(left, 0) - symbolToCount.getOrDefault(right, 0));
    }
    for (char left = '0', right = '9'; left < right; ++left, --right) {
      result +=
          Math.abs(symbolToCount.getOrDefault(left, 0) - symbolToCount.getOrDefault(right, 0));
    }

    return result;
  }
}