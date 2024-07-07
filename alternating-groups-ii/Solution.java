class Solution {
  public int numberOfAlternatingGroups(int[] colors, int k) {
    int count = 0;
    for (int i = 0; i < k - 3; ++i) {
      if (!isAlternating(colors, i)) {
        ++count;
      }
    }

    int result = 0;
    for (int i = 0; i < colors.length; ++i) {
      if (!isAlternating(colors, (i + k - 3) % colors.length)) {
        ++count;
      }

      if (count == 0) {
        ++result;
      }

      if (!isAlternating(colors, i)) {
        --count;
      }
    }

    return result;
  }

  boolean isAlternating(int[] colors, int index) {
    return colors[index] != colors[Math.floorMod(index - 1, colors.length)]
        && colors[index] != colors[(index + 1) % colors.length];
  }
}