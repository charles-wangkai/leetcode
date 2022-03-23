class Solution {
  public int brokenCalc(int startValue, int target) {
    int operationNum = 0;
    while (target > startValue) {
      if (target % 2 != 0) {
        ++target;
        ++operationNum;
      }

      target /= 2;
      ++operationNum;
    }
    operationNum += startValue - target;

    return operationNum;
  }
}
