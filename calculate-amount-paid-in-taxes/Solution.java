class Solution {
  public double calculateTax(int[][] brackets, int income) {
    double result = 0;
    int rest = income;
    int prev = 0;
    for (int[] bracket : brackets) {
      int delta = Math.min(bracket[0] - prev, rest);
      result += delta * bracket[1] / 100.0;
      rest -= delta;

      prev = bracket[0];
    }

    return result;
  }
}