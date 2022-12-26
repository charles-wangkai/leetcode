class Solution {
  public int meetRequirement(int n, int[][] lights, int[] requirement) {
    int[] deltas = new int[n + 1];
    for (int[] light : lights) {
      ++deltas[Math.max(0, light[0] - light[1])];
      --deltas[Math.min(n - 1, light[0] + light[1]) + 1];
    }

    int result = 0;
    int brightness = 0;
    for (int i = 0; i < requirement.length; ++i) {
      brightness += deltas[i];
      if (brightness >= requirement[i]) {
        ++result;
      }
    }

    return result;
  }
}
