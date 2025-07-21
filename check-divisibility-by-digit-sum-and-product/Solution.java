class Solution {
  public boolean checkDivisibility(int n) {
    return n % (computeDigitSum(n) + computeDigitProduct(n)) == 0;
  }

  int computeDigitSum(int n) {
    return String.valueOf(n).chars().map(c -> c - '0').sum();
  }

  int computeDigitProduct(int n) {
    return String.valueOf(n).chars().map(c -> c - '0').reduce((acc, x) -> acc * x).getAsInt();
  }
}