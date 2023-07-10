class Solution {
  public boolean checkArray(int[] nums, int k) {
    int[] increaseNums = new int[nums.length + 1];
    int decreaseNum = 0;
    for (int i = 0; i < nums.length; ++i) {
      decreaseNum -= increaseNums[i];

      int operationNum = nums[i] - decreaseNum;
      if (operationNum < 0) {
        return false;
      }

      if (i + k < increaseNums.length) {
        decreaseNum += operationNum;
        increaseNums[i + k] = operationNum;
      } else if (operationNum != 0) {
        return false;
      }
    }

    return true;
  }
}
