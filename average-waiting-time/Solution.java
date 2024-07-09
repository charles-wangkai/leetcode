class Solution {
  public double averageWaitingTime(int[][] customers) {
    long waitingTimeSum = 0;
    int startTime = 0;
    for (int[] customer : customers) {
      startTime = Math.max(startTime, customer[0]) + customer[1];
      waitingTimeSum += startTime - customer[0];
    }

    return (double) waitingTimeSum / customers.length;
  }
}
