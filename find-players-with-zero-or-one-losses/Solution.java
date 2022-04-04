import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public List<List<Integer>> findWinners(int[][] matches) {
    Map<Integer, Integer> playerToLoseCount = new HashMap<>();
    for (int[] match : matches) {
      if (!playerToLoseCount.containsKey(match[0])) {
        playerToLoseCount.put(match[0], 0);
      }

      playerToLoseCount.put(match[1], playerToLoseCount.getOrDefault(match[1], 0) + 1);
    }

    return List.of(findPlayers(playerToLoseCount, 0), findPlayers(playerToLoseCount, 1));
  }

  List<Integer> findPlayers(Map<Integer, Integer> playerToLoseCount, int loseCount) {
    return playerToLoseCount.keySet().stream()
        .filter(player -> playerToLoseCount.get(player) == loseCount)
        .sorted()
        .collect(Collectors.toList());
  }
}