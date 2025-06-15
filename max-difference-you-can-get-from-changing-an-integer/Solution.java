class Solution {
  public int maxDiff(int num) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int x = 0; x <= 9; ++x) {
      for (int y = 0; y <= 9; ++y) {
        String replaced = String.valueOf(num).replace((char) (x + '0'), (char) (y + '0'));
        if (replaced.charAt(0) != '0') {
          int value = Integer.parseInt(replaced);

          min = Math.min(min, value);
          max = Math.max(max, value);
        }
      }
    }

    return max - min;
  }
}