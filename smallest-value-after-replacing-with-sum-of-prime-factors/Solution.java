class Solution {
  public int smallestValue(int n) {
    int sum = 0;
    int current = n;
    for (int i = 2; i * i <= current; ++i) {
      while (current % i == 0) {
        sum += i;
        current /= i;
      }
    }
    if (current != 1) {
      sum += current;
    }

    return (sum == n) ? sum : smallestValue(sum);
  }
}
