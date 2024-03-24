class Solution {
  public int minOperations(int k) {
    int result = Integer.MAX_VALUE;
    for (int incrementCount = 0; incrementCount <= k - 1; ++incrementCount) {
      result = Math.min(result, incrementCount + ((k + incrementCount) / (1 + incrementCount) - 1));
    }

    return result;
  }
}