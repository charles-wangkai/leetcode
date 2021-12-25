class Solution {
  public boolean canBeValid(String s, String locked) {
    if (s.length() % 2 != 0) {
      return false;
    }

    int maxDepth = 0;
    int minDepth = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (locked.charAt(i) == '1') {
        if (s.charAt(i) == '(') {
          ++maxDepth;
          ++minDepth;
        } else {
          if (maxDepth == 0) {
            return false;
          }

          --maxDepth;

          if (minDepth == 0) {
            minDepth = 1;
          } else {
            --minDepth;
          }
        }
      } else {
        ++maxDepth;

        if (minDepth == 0) {
          minDepth = 1;
        } else {
          --minDepth;
        }
      }
    }

    return minDepth == 0;
  }
}