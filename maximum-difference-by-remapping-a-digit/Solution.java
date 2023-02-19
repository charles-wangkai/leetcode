class Solution {
  public int minMaxDifference(int num) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int from = 0; from <= 9; ++from) {
      for (int to = 0; to <= 9; ++to) {
        int value =
            Integer.parseInt(String.valueOf(num).replace((char) (from + '0'), (char) (to + '0')));
        min = Math.min(min, value);
        max = Math.max(max, value);
      }
    }

    return max - min;
  }
}
