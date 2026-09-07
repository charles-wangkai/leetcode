class Solution {
  public int countGroups(int[] position, int[] speed, int distance) {
    int result = 1;
    int groupSpeed = speed[speed.length - 1];
    for (int i = position.length - 2; i >= 0; --i) {
      if (position[i + 1] - position[i] > distance && speed[i] <= groupSpeed) {
        ++result;
        groupSpeed = speed[i];
      }
    }

    return result;
  }
}