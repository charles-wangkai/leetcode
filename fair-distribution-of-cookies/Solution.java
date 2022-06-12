class Solution {
  public int distributeCookies(int[] cookies, int k) {
    return search(k, cookies, 0);
  }

  int search(int k, int[] cookies, int index) {
    if (index == cookies.length) {
      return computeUnfairness(cookies, k);
    }

    int result = Integer.MAX_VALUE;
    for (int i = index; i < cookies.length; ++i) {
      swap(cookies, i, index);
      result = Math.min(result, search(k, cookies, index + 1));
      swap(cookies, i, index);
    }

    return result;
  }

  int computeUnfairness(int[] cookies, int k) {
    int result = Integer.MAX_VALUE;
    for (int mask = 1 << (cookies.length - 1); mask < 1 << cookies.length; ++mask) {
      if (Integer.bitCount(mask) == k) {
        int unfairness = 0;
        int sum = 0;
        for (int i = 0; i < cookies.length; ++i) {
          sum += cookies[i];
          if ((mask & (1 << i)) != 0) {
            unfairness = Math.max(unfairness, sum);
            sum = 0;
          }
        }

        result = Math.min(result, unfairness);
      }
    }

    return result;
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}