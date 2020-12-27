class Solution {
  public int countStudents(int[] students, int[] sandwiches) {
    int[] counts = new int[2];
    for (int student : students) {
      ++counts[student];
    }

    for (int i = 0; i < sandwiches.length; ++i) {
      if (counts[sandwiches[i]] == 0) {
        return sandwiches.length - i;
      }

      --counts[sandwiches[i]];
    }

    return 0;
  }
}
