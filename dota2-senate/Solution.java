import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  public String predictPartyVictory(String senate) {
    Queue<Integer> radiant = new ArrayDeque<>();
    Queue<Integer> dire = new ArrayDeque<>();

    int sequence = 0;
    for (int i = 0; i < senate.length(); ++i) {
      if (senate.charAt(i) == 'R') {
        radiant.offer(sequence);
      } else {
        dire.offer(sequence);
      }

      ++sequence;
    }

    while (!radiant.isEmpty() && !dire.isEmpty()) {
      int radiantHead = radiant.poll();
      int direHead = dire.poll();

      if (radiantHead < direHead) {
        radiant.offer(sequence);
      } else {
        dire.offer(sequence);
      }

      ++sequence;
    }

    return dire.isEmpty() ? "Radiant" : "Dire";
  }
}
