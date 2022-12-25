import java.util.Arrays;

class Solution {
  public int maximumTastiness(int[] price, int k) {
    Arrays.sort(price);

    int result = -1;
    int lower = 0;
    int upper = price[price.length - 1] - price[0];
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(price, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] price, int k, int diff) {
    int count = 0;
    int prev = -diff;
    for (int p : price) {
      if (p - prev >= diff) {
        ++count;
        prev = p;
      }
    }

    return count >= k;
  }
}
