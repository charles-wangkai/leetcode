import java.util.Arrays;

class Solution {
  public boolean asteroidsDestroyed(int mass, int[] asteroids) {
    Arrays.sort(asteroids);

    long current = mass;
    for (int asteroid : asteroids) {
      if (asteroid > current) {
        return false;
      }

      current += asteroid;
    }

    return true;
  }
}