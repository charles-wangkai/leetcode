class Solution {
  public int numberOfArrays(int[] differences, int lower, int upper) {
    long min = 0;
    long max = 0;
    long current = 0;
    for (int difference : differences) {
      current += difference;
      min = Math.min(min, current);
      max = Math.max(max, current);
    }

    return (int) Math.max(0, (upper - lower) - (max - min) + 1);
  }
}