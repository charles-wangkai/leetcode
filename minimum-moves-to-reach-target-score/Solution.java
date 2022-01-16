class Solution {
  public int minMoves(int target, int maxDoubles) {
    int result = 0;
    int current = target;
    int doubleRest = maxDoubles;
    while (current != 1 && doubleRest != 0) {
      if (current % 2 == 0) {
        current /= 2;
        --doubleRest;
      } else {
        --current;
      }

      ++result;
    }
    result += current - 1;

    return result;
  }
}