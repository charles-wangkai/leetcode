class Solution {
  static final int LIMIT = 1000;

  public boolean carPooling(int[][] trips, int capacity) {
    int[] deltas = new int[LIMIT + 1];
    for (int[] trip : trips) {
      deltas[trip[1]] += trip[0];
      deltas[trip[2]] -= trip[0];
    }

    int passengerNum = 0;
    for (int i = 0; i < deltas.length; ++i) {
      passengerNum += deltas[i];

      if (passengerNum > capacity) {
        return false;
      }
    }

    return true;
  }
}
