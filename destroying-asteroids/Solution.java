import java.util.Arrays;

class Solution {
  public boolean asteroidsDestroyed(int mass, int[] asteroids) {
    long current = mass;
    int[] sorted = Arrays.stream(asteroids).boxed().sorted().mapToInt(x -> x).toArray();
    for (int asteroid : sorted) {
      if (current < asteroid) {
        return false;
      }

      current += asteroid;
    }

    return true;
  }
}