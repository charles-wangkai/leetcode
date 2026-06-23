class Solution {
  public int minLights(int[] lights) {
    int n = lights.length;

    int[] deltas = new int[n + 1];
    for (int i = 0; i < lights.length; ++i) {
      if (lights[i] != 0) {
        ++deltas[Math.max(0, i - lights[i])];
        --deltas[Math.min(n - 1, i + lights[i]) + 1];
      }
    }

    int result = 0;
    int covered = 0;
    for (int i = 0; i < n; ++i) {
      covered += deltas[i];
      if (covered == 0) {
        ++covered;
        --deltas[Math.min(deltas.length - 1, i + 3)];

        ++result;
      }
    }

    return result;
  }
}