class Solution {
  public int totalReplacements(int[] ranks) {
    int result = 0;
    int selected = ranks[0];
    for (int i = 1; i < ranks.length; ++i) {
      if (ranks[i] < selected) {
        selected = ranks[i];
        ++result;
      }
    }

    return result;
  }
}