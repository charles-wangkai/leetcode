class Solution {
  public int countCollisions(String directions) {
    int result = 0;
    int rightCount = 0;
    boolean hasStay = false;
    for (char direction : directions.toCharArray()) {
      if (direction == 'L') {
        if (rightCount != 0) {
          result += rightCount + 1;
          rightCount = 0;
          hasStay = true;
        } else if (hasStay) {
          ++result;
        }
      } else if (direction == 'R') {
        ++rightCount;
        hasStay = false;
      } else {
        if (rightCount != 0) {
          result += rightCount;
          rightCount = 0;
        }

        hasStay = true;
      }
    }

    return result;
  }
}