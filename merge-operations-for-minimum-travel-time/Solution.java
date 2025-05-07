import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
  public int minTravelTime(int l, int n, int k, int[] position, int[] time) {
    State initialState = new State(0, time[0], k);

    Map<State, Integer> stateToMinTime = new HashMap<>();
    stateToMinTime.put(initialState, 0);

    PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparing(State::lastIndex));
    pq.offer(initialState);

    while (!pq.isEmpty()) {
      State state = pq.poll();

      int nextLastTime = 0;
      for (int nextLastIndex = state.lastIndex() + 1;
          nextLastIndex < n && nextLastIndex - state.lastIndex() - 1 <= state.restMergeNum();
          ++nextLastIndex) {
        nextLastTime += time[nextLastIndex];
        State nextState =
            new State(
                nextLastIndex,
                nextLastTime,
                state.restMergeNum() - (nextLastIndex - state.lastIndex() - 1));
        if (!stateToMinTime.containsKey(nextState)) {
          pq.offer(nextState);
        }
        stateToMinTime.put(
            nextState,
            Math.min(
                stateToMinTime.getOrDefault(nextState, Integer.MAX_VALUE),
                stateToMinTime.get(state)
                    + state.lastTime() * (position[nextLastIndex] - position[state.lastIndex()])));
      }
    }

    return stateToMinTime.keySet().stream()
        .filter(state -> state.lastIndex() == n - 1 && state.restMergeNum() == 0)
        .mapToInt(stateToMinTime::get)
        .min()
        .getAsInt();
  }
}

record State(int lastIndex, int lastTime, int restMergeNum) {}
