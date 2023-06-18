class Solution {
  public int distanceTraveled(int mainTank, int additionalTank) {
    int result = 0;
    while (mainTank != 0) {
      result += 10;
      --mainTank;

      if (additionalTank != 0 && result / 10 % 5 == 0) {
        ++mainTank;
        --additionalTank;
      }
    }

    return result;
  }
}
