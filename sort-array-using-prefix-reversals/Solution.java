import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
  public int sortArray(int[] nums, int[] pre) {
    String state = Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.joining());

    Map<String, Integer> stateToDistance = new HashMap<>();
    stateToDistance.put(state, 0);

    Queue<String> queue = new ArrayDeque<>();
    queue.offer(state);

    while (!queue.isEmpty()) {
      String head = queue.poll();
      for (int length : pre) {
        char[] cs = head.toCharArray();
        for (int i = 0, j = length - 1; i < j; ++i, --j) {
          char temp = cs[i];
          cs[i] = cs[j];
          cs[j] = temp;
        }

        String nextState = String.valueOf(cs);
        if (!stateToDistance.containsKey(nextState)) {
          stateToDistance.put(nextState, stateToDistance.get(head) + 1);
          queue.offer(nextState);
        }
      }
    }

    return stateToDistance.getOrDefault(
        Arrays.stream(nums).sorted().mapToObj(String::valueOf).collect(Collectors.joining()), -1);
  }
}