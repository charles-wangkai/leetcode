import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<List<Integer>> groupThePeople(int[] groupSizes) {
    Map<Integer, Deque<Integer>> sizeToIds = new HashMap<>();
    for (int i = 0; i < groupSizes.length; ++i) {
      sizeToIds.putIfAbsent(groupSizes[i], new ArrayDeque<>());
      sizeToIds.get(groupSizes[i]).push(i);
    }

    List<List<Integer>> result = new ArrayList<>();
    for (int size : sizeToIds.keySet()) {
      Deque<Integer> ids = sizeToIds.get(size);
      while (!ids.isEmpty()) {
        List<Integer> group = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
          group.add(ids.pop());
        }

        result.add(group);
      }
    }

    return result;
  }
}
