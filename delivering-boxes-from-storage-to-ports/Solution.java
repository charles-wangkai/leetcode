class Solution {
  public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
    int n = boxes.length;

    int[] dp = new int[n + 1];
    int weightSum = 0;
    int start = 0;
    int diffPortNum = 0;
    for (int i = 0; i < n; ++i) {
      weightSum += boxes[i][1];
      if (i == 0 || boxes[i][0] != boxes[i - 1][0]) {
        ++diffPortNum;
      }

      while (i - start == maxBoxes
          || weightSum > maxWeight
          || (start < i && dp[start] == dp[start + 1])) {
        weightSum -= boxes[start][1];
        if (start == n - 1 || boxes[start][0] != boxes[start + 1][0]) {
          --diffPortNum;
        }
        ++start;
      }

      dp[i + 1] = dp[start] + diffPortNum + 1;
    }

    return dp[n];
  }
}
