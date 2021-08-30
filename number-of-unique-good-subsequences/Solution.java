class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfUniqueGoodSubsequences(String binary) {
    int endZeroNum = 0;
    int endOneNum = 0;
    for (char ch : binary.toCharArray()) {
      if (ch == '0') {
        endZeroNum = addMod(endZeroNum, endOneNum);
      } else {
        endOneNum = addMod(addMod(endZeroNum, endOneNum), 1);
      }
    }

    return addMod(addMod(endZeroNum, endOneNum), (binary.indexOf('0') == -1) ? 0 : 1);
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}
