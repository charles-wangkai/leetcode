class Solution {
  public int smallestNumber(int n, int t) {
    int result = n;
    while (String.valueOf(result).chars().map(c -> c - '0').reduce((acc, x) -> acc * x).getAsInt()
            % t
        != 0) {
      ++result;
    }

    return result;
  }
}