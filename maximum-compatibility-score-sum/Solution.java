class Solution {
  public int maxCompatibilitySum(int[][] students, int[][] mentors) {
    return search(students, mentors, 0);
  }

  int search(int[][] students, int[][] mentors, int mentorIndex) {
    if (mentorIndex == mentors.length) {
      return computeScore(students, mentors);
    }

    int result = 0;
    for (int i = mentorIndex; i < mentors.length; ++i) {
      swap(mentors, i, mentorIndex);
      result = Math.max(result, search(students, mentors, mentorIndex + 1));
      swap(mentors, i, mentorIndex);
    }

    return result;
  }

  int computeScore(int[][] students, int[][] mentors) {
    int result = 0;
    for (int i = 0; i < students.length; ++i) {
      for (int j = 0; j < students[i].length; ++j) {
        result += (students[i][j] == mentors[i][j]) ? 1 : 0;
      }
    }

    return result;
  }

  void swap(int[][] a, int index1, int index2) {
    int[] temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}
