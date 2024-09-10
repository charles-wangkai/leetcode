// Definition of commonBits API (defined in the parent class Problem).
class Problem {
  int commonBits(int num) {
    throw new UnsupportedOperationException();
  }
}

public class Solution extends Problem {
  public int findNumber() {
    int result = 0;
    for (int i = 0; i < 30; ++i) {
      if (computeCommonBitNum(1 << i) > computeCommonBitNum(0)) {
        result += 1 << i;
      }
    }

    return result;
  }

  int computeCommonBitNum(int value) {
    int result = commonBits(value);
    commonBits(value);

    return result;
  }
}