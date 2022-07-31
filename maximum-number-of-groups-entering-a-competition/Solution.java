class Solution {
  public int maximumGroups(int[] grades) {
    int result = 0;
    int sum = 0;
    for (int i = 1; sum + i <= grades.length; ++i) {
      sum += i;
      ++result;
    }

    return result;
  }
}