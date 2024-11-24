import java.util.stream.IntStream;

class Solution {
  public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
    long[][] nextDistances = new long[26][26];
    for (int i = 0; i < nextDistances.length; ++i) {
      int current = i;
      for (int step = 1; step < 26; ++step) {
        nextDistances[i][(current + 1) % 26] = nextDistances[i][current] + nextCost[current];
        current = (current + 1) % 26;
      }
    }

    long[][] previousDistances = new long[26][26];
    for (int i = 0; i < previousDistances.length; ++i) {
      int current = i;
      for (int step = 1; step < 26; ++step) {
        previousDistances[i][(current + 25) % 26] =
            previousDistances[i][current] + previousCost[current];
        current = (current + 25) % 26;
      }
    }

    return IntStream.range(0, s.length())
        .mapToLong(
            i ->
                Math.min(
                    nextDistances[s.charAt(i) - 'a'][t.charAt(i) - 'a'],
                    previousDistances[s.charAt(i) - 'a'][t.charAt(i) - 'a']))
        .sum();
  }
}