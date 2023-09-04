import java.util.stream.IntStream;

class Solution {
  public int countSymmetricIntegers(int low, int high) {
    return (int) IntStream.rangeClosed(low, high).filter(this::isSymmetric).count();
  }

  boolean isSymmetric(int x) {
    int[] digits = String.valueOf(x).chars().map(c -> c - '0').toArray();

    return digits.length % 2 == 0
        && IntStream.range(0, digits.length / 2).map(i -> digits[i]).sum()
            == IntStream.range(digits.length / 2, digits.length).map(i -> digits[i]).sum();
  }
}
