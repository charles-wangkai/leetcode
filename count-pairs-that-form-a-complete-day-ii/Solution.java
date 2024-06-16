class Solution {
  public long countCompleteDayPairs(int[] hours) {
    int[] counts = new int[24];
    for (int hour : hours) {
      ++counts[hour % counts.length];
    }

    long result = 0;
    for (int i = 0; i < counts.length; ++i) {
      int other = (counts.length - i) % counts.length;
      if (other == i) {
        result += counts[i] * (counts[i] - 1L) / 2;
      } else if (other > i) {
        result += (long) counts[i] * counts[other];
      }
    }

    return result;
  }
}