import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
    Map<String, String> regionToParent = new HashMap<>();
    for (List<String> group : regions) {
      for (int i = 1; i < group.size(); ++i) {
        regionToParent.put(group.get(i), group.get(0));
      }
    }

    List<String> path1 = findPath(regionToParent, region1);
    List<String> path2 = findPath(regionToParent, region2);

    int index = 0;
    while (index != path1.size() - 1
        && index != path2.size() - 1
        && path1.get(index + 1).equals(path2.get(index + 1))) {
      ++index;
    }

    return path1.get(index);
  }

  List<String> findPath(Map<String, String> regionToParent, String region) {
    List<String> result = new ArrayList<>();
    while (true) {
      result.add(region);
      if (!regionToParent.containsKey(region)) {
        break;
      }

      region = regionToParent.get(region);
    }
    Collections.reverse(result);

    return result;
  }
}
