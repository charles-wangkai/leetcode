class Solution {
  public int earliestFinishTime(
      int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < landStartTime.length; ++i) {
      for (int j = 0; j < waterStartTime.length; ++j) {
        result =
            Math.min(
                result,
                Math.min(
                    computeFinishTime(
                        landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j]),
                    computeFinishTime(
                        waterStartTime[j], waterDuration[j], landStartTime[i], landDuration[i])));
      }
    }

    return result;
  }

  int computeFinishTime(int startTime1, int duration1, int startTime2, int duration2) {
    return Math.max(startTime1 + duration1, startTime2) + duration2;
  }
}