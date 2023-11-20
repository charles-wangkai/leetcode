import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int garbageCollection(String[] garbage, int[] travel) {
    Map<Character, Integer> typeToTravelTime = new HashMap<>();
    int travelTime = 0;
    for (int i = 1; i < garbage.length; ++i) {
      travelTime += travel[i - 1];
      for (char type : garbage[i].toCharArray()) {
        typeToTravelTime.put(type, travelTime);
      }
    }

    return Arrays.stream(garbage).mapToInt(String::length).sum()
        + typeToTravelTime.values().stream().mapToInt(Integer::intValue).sum();
  }
}
