class Solution {
  public int passThePillow(int n, int time) {
    int result = 1;
    int offset = 1;
    for (int i = 0; i < time; ++i) {
      if ((offset == 1 && result == n) || (offset == -1 && result == 1)) {
        offset *= -1;
      }

      result += offset;
    }

    return result;
  }
}
