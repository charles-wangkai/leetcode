class Solution {
  public int minInsertions(String s) {
    int length = s.length();

    int[][] stepNums = new int[length][length];
    for (int size = 2; size <= length; ++size) {
      for (int beginIndex = 0; beginIndex + size <= length; ++beginIndex) {
        int endIndex = beginIndex + size - 1;

        if (s.charAt(beginIndex) == s.charAt(endIndex)) {
          stepNums[beginIndex][endIndex] = stepNums[beginIndex + 1][endIndex - 1];
        } else {
          stepNums[beginIndex][endIndex] =
              Math.min(stepNums[beginIndex + 1][endIndex], stepNums[beginIndex][endIndex - 1]) + 1;
        }
      }
    }

    return stepNums[0][length - 1];
  }
}
