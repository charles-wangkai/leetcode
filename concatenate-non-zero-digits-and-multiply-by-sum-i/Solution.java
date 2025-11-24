class Solution {
  public long sumAndMultiply(int n) {
    if (n == 0) {
      return 0;
    }

    return (long) Integer.parseInt(String.valueOf(n).replace("0", ""))
        * String.valueOf(n).chars().map(c -> c - '0').sum();
  }
}