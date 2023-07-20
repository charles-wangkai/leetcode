import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
    int baseCount = 0;
    List<Double> thetas = new ArrayList<>();
    for (List<Integer> point : points) {
      if (point.get(0).equals(location.get(0)) && point.get(1).equals(location.get(1))) {
        ++baseCount;
      } else {
        double theta = Math.atan2(point.get(1) - location.get(1), point.get(0) - location.get(0));
        thetas.add(theta);
        thetas.add(theta + Math.PI * 2);
      }
    }

    Collections.sort(thetas);

    int result = baseCount;
    int beginIndex = 0;
    for (int endIndex = 0; endIndex < thetas.size(); ++endIndex) {
      while (thetas.get(endIndex) - thetas.get(beginIndex) > angle * Math.PI / 180) {
        ++beginIndex;
      }

      result = Math.max(result, baseCount + (endIndex - beginIndex + 1));
    }

    return result;
  }
}
