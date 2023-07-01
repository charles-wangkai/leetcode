class Solution {
  public int distributeCookies(int[] cookies, int k) {
    return search(cookies, new int[k], 0, 0);
  }

  int search(int[] cookies, int[] sums, int maxSum, int index) {
    if (index == cookies.length) {
      return maxSum;
    }

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < sums.length; ++i) {
      sums[i] += cookies[index];
      result = Math.min(result, search(cookies, sums, Math.max(maxSum, sums[i]), index + 1));
      sums[i] -= cookies[index];
    }

    return result;
  }
}
