import java.util.stream.IntStream;

class Solution {
  static final int SECTION_NUM = 12;

  public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
    int maxScore = -1;
    int[] result = null;
    for (int mask = 0; mask <= 1 << SECTION_NUM; ++mask) {
      boolean[] wins = new boolean[SECTION_NUM];
      for (int i = 0; i < wins.length; ++i) {
        wins[i] = (mask & (1 << i)) != 0;
      }

      int needed =
          IntStream.range(0, wins.length).filter(i -> wins[i]).map(i -> aliceArrows[i] + 1).sum();
      if (needed <= numArrows) {
        int score = IntStream.range(0, wins.length).filter(i -> wins[i]).sum();
        if (score > maxScore) {
          maxScore = score;

          result =
              IntStream.range(0, wins.length)
                  .map(i -> wins[i] ? (aliceArrows[i] + 1) : 0)
                  .toArray();
          result[0] += numArrows - needed;
        }
      }
    }

    return result;
  }
}