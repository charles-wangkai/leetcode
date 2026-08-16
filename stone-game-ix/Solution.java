class Solution {
  public boolean stoneGameIX(int[] stones) {
    int[] counts = new int[3];
    for (int stone : stones) {
      ++counts[stone % 3];
    }

    return isAliceWin(counts, 1) || isAliceWin(counts, 2);
  }

  boolean isAliceWin(int[] counts, int firstChosen) {
    int[] rests = counts.clone();
    if (rests[firstChosen] == 0) {
      return false;
    }
    --rests[firstChosen];

    boolean aliceTurn = rests[0] % 2 == 1;
    int total = rests[1] + rests[2];
    for (int i = 0; i < total; ++i) {
      int target = (i % 2 == 0) ? firstChosen : (3 - firstChosen);

      if (rests[target] == 0) {
        return !aliceTurn;
      }
      --rests[target];

      aliceTurn ^= true;
    }

    return false;
  }
}
