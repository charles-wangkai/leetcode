import java.util.stream.IntStream;

class Solution {
  public int isWinner(int[] player1, int[] player2) {
    int score1 = computeScore(player1);
    int score2 = computeScore(player2);

    if (score1 > score2) {
      return 1;
    }
    if (score1 < score2) {
      return 2;
    }

    return 0;
  }

  int computeScore(int[] pins) {
    return IntStream.range(0, pins.length)
        .map(
            i ->
                ((i >= 2 && pins[i - 2] == 10) || (i >= 1 && pins[i - 1] == 10))
                    ? (2 * pins[i])
                    : pins[i])
        .sum();
  }
}
