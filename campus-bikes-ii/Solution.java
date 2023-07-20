public class Solution {
  int minDistanceSum;

  public int assignBikes(int[][] workers, int[][] bikes) {
    minDistanceSum = Integer.MAX_VALUE;
    search(workers, bikes, 0);
    return minDistanceSum;
  }

  void search(int[][] workers, int[][] bikes, int index) {
    if (index == workers.length) {
      int distanceSum = 0;
      for (int i = 0; i < workers.length; i++) {
        distanceSum += computeDistance(workers[i], bikes[i]);
      }

      minDistanceSum = Math.min(minDistanceSum, distanceSum);

      return;
    }

    for (int i = index; i < bikes.length; i++) {
      swap(bikes, i, index);
      search(workers, bikes, index + 1);
      swap(bikes, i, index);
    }
  }

  int computeDistance(int[] worker, int[] bike) {
    return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
  }

  void swap(int[][] a, int index1, int index2) {
    int[] temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}
