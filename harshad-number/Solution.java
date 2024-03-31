class Solution {
  public int sumOfTheDigitsOfHarshadNumber(int x) {
    int digitSum = String.valueOf(x).chars().map(c -> c - '0').sum();

    return (x % digitSum == 0) ? digitSum : -1;
  }
}