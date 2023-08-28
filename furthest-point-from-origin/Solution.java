class Solution {
  public int furthestDistanceFromOrigin(String moves) {
    int position = 0;
    int freeCount = 0;
    for (char move : moves.toCharArray()) {
      if (move == 'L') {
        --position;
      } else if (move == 'R') {
        ++position;
      } else {
        ++freeCount;
      }
    }

    return Math.max(Math.abs(position - freeCount), Math.abs(position + freeCount));
  }
}
