class Solution {
  public int maxDistance(int[] colors) {
    int result = 0;
    for (int i = 0; i < colors.length; ++i) {
      for (int j = i + 1; j < colors.length; ++j) {
        if (colors[j] != colors[i]) {
          result = Math.max(result, j - i);
        }
      }
    }

    return result;
  }
}
