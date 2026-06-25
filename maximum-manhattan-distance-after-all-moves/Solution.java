class Solution {
  public int maxDistance(String moves) {
    int x = 0;
    int y = 0;
    int freeCount = 0;
    for (char move : moves.toCharArray()) {
      if (move == 'U') {
        ++y;
      } else if (move == 'D') {
        --y;
      } else if (move == 'L') {
        --x;
      } else if (move == 'R') {
        ++x;
      } else {
        ++freeCount;
      }
    }

    return Math.abs(x) + Math.abs(y) + freeCount;
  }
}