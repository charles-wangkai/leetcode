class Solution {
  public int twoEggDrop(int n) {
    return superEggDrop(2, n);
  }

  int superEggDrop(int k, int n) {
    int[][] maxFloors = new int[n + 1][k + 1];
    for (int move = 1; ; ++move) {
      for (int i = 1; i <= k; ++i) {
        maxFloors[move][i] = maxFloors[move - 1][i - 1] + maxFloors[move - 1][i] + 1;
      }

      if (maxFloors[move][k] >= n) {
        return move;
      }
    }
  }
}
