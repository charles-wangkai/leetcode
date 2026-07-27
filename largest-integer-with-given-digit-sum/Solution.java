class Solution {
  public int largestInteger(int n, int s) {
    int result = -1;
    for (int i = 0; String.valueOf(i).length() <= n; ++i) {
      if (String.valueOf(i).chars().map(c -> c - '0').sum() == s) {
        result = i;
      }
    }

    return result;
  }
}