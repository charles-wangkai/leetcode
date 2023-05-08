class Solution {
  int incrementNum;

  public int minIncrements(int n, int[] cost) {
    int maxPathSum = findMaxPathSum(cost, 0, 0);

    incrementNum = 0;
    search(cost, maxPathSum, 0, 0);

    return incrementNum;
  }

  int search(int[] cost, int maxPathSum, int pathSum, int index) {
    pathSum += cost[index];

    if (index * 2 + 1 >= cost.length) {
      int delta = maxPathSum - pathSum;
      incrementNum += delta;

      return delta;
    }

    int leftDelta = search(cost, maxPathSum, pathSum, index * 2 + 1);
    int rightDelta = search(cost, maxPathSum, pathSum, index * 2 + 2);
    int common = Math.min(leftDelta, rightDelta);
    incrementNum -= common;

    return common;
  }

  int findMaxPathSum(int[] cost, int pathSum, int index) {
    pathSum += cost[index];

    return (index * 2 + 1 < cost.length)
        ? Math.max(
            findMaxPathSum(cost, pathSum, index * 2 + 1),
            findMaxPathSum(cost, pathSum, index * 2 + 2))
        : pathSum;
  }
}
