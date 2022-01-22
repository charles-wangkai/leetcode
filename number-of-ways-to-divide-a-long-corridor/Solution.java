class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfWays(String corridor) {
    int seatNum = (int) corridor.chars().filter(ch -> ch == 'S').count();
    if (seatNum <= 1 || seatNum % 2 != 0) {
      return 0;
    }

    int result = 1;
    int seatCount = 0;
    int plantCount = 0;
    for (int i = corridor.indexOf('S'); i < corridor.length(); ++i) {
      if (corridor.charAt(i) == 'S') {
        ++seatCount;
        if (seatCount % 2 != 0) {
          result = multiplyMod(result, plantCount + 1);
        }
        plantCount = 0;
      } else {
        ++plantCount;
      }
    }

    return result;
  }

  static int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}