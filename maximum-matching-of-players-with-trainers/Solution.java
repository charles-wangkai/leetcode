import java.util.Arrays;

class Solution {
  public int matchPlayersAndTrainers(int[] players, int[] trainers) {
    Arrays.sort(players);
    Arrays.sort(trainers);

    int result = 0;
    int trainerIndex = 0;
    for (int player : players) {
      while (trainerIndex != trainers.length && trainers[trainerIndex] < player) {
        ++trainerIndex;
      }

      if (trainerIndex != trainers.length) {
        ++result;
        ++trainerIndex;
      }
    }

    return result;
  }
}