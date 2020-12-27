class Solution {
  public double averageWaitingTime(int[][] customers) {
    double waitingTimeSum = 0;
    int chefStartTime = 0;
    for (int[] customer : customers) {
      chefStartTime = Math.max(chefStartTime, customer[0]) + customer[1];
      waitingTimeSum += chefStartTime - customer[0];
    }

    return (double) waitingTimeSum / customers.length;
  }
}
