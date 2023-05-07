import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
  public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
    int[] result = new int[obstacles.length];
    NavigableMap<Integer, Integer> obstacleToLength = new TreeMap<>();
    for (int i = 0; i < result.length; ++i) {
      Integer floorObstacle = obstacleToLength.floorKey(obstacles[i]);
      int length = (floorObstacle == null) ? 1 : (obstacleToLength.get(floorObstacle) + 1);

      result[i] = length;
      obstacleToLength.put(obstacles[i], length);

      while (true) {
        Integer higherObstacle = obstacleToLength.higherKey(obstacles[i]);
        if (higherObstacle == null || obstacleToLength.get(higherObstacle) >= length) {
          break;
        }

        obstacleToLength.remove(higherObstacle);
      }
    }

    return result;
  }
}
