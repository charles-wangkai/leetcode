class Solution {
  public long distributeCandies(int n, int limit) {
    long result = 0;
    for (int i = 0; i <= n && i <= limit; ++i) {
      result += computeWayNum(limit, n - i);
    }

    return result;
  }

  int computeWayNum(int limit, int sum) {
    if (limit * 2 < sum) {
      return 0;
    }
    if (limit >= sum) {
      return sum + 1;
    }

    return limit * 2 - sum + 1;
  }
}
