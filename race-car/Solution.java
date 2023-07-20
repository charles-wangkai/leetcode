import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class Solution {
  public int racecar(int target) {
    Map<State, Integer> state2length = new HashMap<>();
    State state = new State(0, 1);
    state2length.put(state, 0);
    Queue<State> queue = new LinkedList<>();
    queue.offer(state);
    while (true) {
      State head = queue.poll();

      int nextLength = state2length.get(head) + 1;
      for (State nextState :
          new State[] {
            new State(head.position + head.speed, head.speed * 2),
            new State(head.position, -Integer.signum(head.speed))
          }) {
        if (nextState.position == target) {
          return nextLength;
        }

        if (nextState.position > 0
            && nextState.position < (1 << 14)
            && !state2length.containsKey(nextState)) {
          state2length.put(nextState, nextLength);
          queue.offer(nextState);
        }
      }
    }
  }
}

class State {
  int position;
  int speed;

  State(int position, int speed) {
    this.position = position;
    this.speed = speed;
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, speed);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;
    return position == other.position && speed == other.speed;
  }
}
