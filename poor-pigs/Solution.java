class Solution {
  public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
    int base = minutesToTest / minutesToDie + 1;
    int pigNum = 0;
    int power = 1;
    while (power < buckets) {
      power *= base;
      ++pigNum;
    }

    return pigNum;
  }
}
