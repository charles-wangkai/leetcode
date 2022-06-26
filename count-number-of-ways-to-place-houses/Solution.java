class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countHousePlacements(int n) {
    int prev = 1;
    int curr = 1;
    for (int i = 0; i < n; ++i) {
      int next = addMod(prev, curr);

      prev = curr;
      curr = next;
    }

    return multiplyMod(curr, curr);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}