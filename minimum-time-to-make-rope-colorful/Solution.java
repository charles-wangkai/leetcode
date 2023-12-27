class Solution {
  public int minCost(String colors, int[] neededTime) {
    int result = 0;
    int costSum = 0;
    int maxCost = 0;
    for (int i = 0; i <= colors.length(); ++i) {
      if (i != colors.length() && i != 0 && colors.charAt(i) == colors.charAt(i - 1)) {
        costSum += neededTime[i];
        maxCost = Math.max(maxCost, neededTime[i]);
      } else {
        result += costSum - maxCost;

        if (i != colors.length()) {
          costSum = neededTime[i];
          maxCost = neededTime[i];
        }
      }
    }

    return result;
  }
}
