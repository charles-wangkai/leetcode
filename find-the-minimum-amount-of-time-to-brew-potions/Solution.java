class Solution {
  public long minTime(int[] skill, int[] mana) {
    long[] endTimes = new long[skill.length];
    for (int m : mana) {
      for (int i = 0; i < endTimes.length; ++i) {
        endTimes[i] = Math.max((i == 0) ? 0 : endTimes[i - 1], endTimes[i]) + skill[i] * m;
      }
      for (int i = endTimes.length - 2; i >= 0; --i) {
        endTimes[i] = endTimes[i + 1] - skill[i + 1] * m;
      }
    }

    return endTimes[endTimes.length - 1];
  }
}