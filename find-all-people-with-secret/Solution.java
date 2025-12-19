import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
    boolean[] secrets = new boolean[n];
    secrets[0] = true;
    secrets[firstPerson] = true;

    Arrays.sort(meetings, Comparator.comparing(meeting -> meeting[2]));

    @SuppressWarnings("unchecked")
    List<Integer>[] indexLists = new List[secrets.length];
    Deque<Integer> todoIndices = new ArrayDeque<>();
    int[] times = new int[n];

    int beginIndex = 0;
    while (beginIndex != meetings.length) {
      int time = meetings[beginIndex][2];

      int endIndex = beginIndex;
      while (endIndex + 1 != meetings.length && meetings[endIndex + 1][2] == time) {
        ++endIndex;
      }

      for (int i = beginIndex; i <= endIndex; ++i) {
        getIndexList(indexLists, times, meetings[i][0], time).add(i);
        getIndexList(indexLists, times, meetings[i][1], time).add(i);
      }

      todoIndices.clear();
      for (int i = beginIndex; i <= endIndex; ++i) {
        if (secrets[meetings[i][0]] ^ secrets[meetings[i][1]]) {
          todoIndices.push(i);
        }
      }

      while (!todoIndices.isEmpty()) {
        int todoIndex = todoIndices.pop();
        if (secrets[meetings[todoIndex][0]] && !secrets[meetings[todoIndex][1]]) {
          secrets[meetings[todoIndex][1]] = true;
          for (int index : getIndexList(indexLists, times, meetings[todoIndex][1], time)) {
            todoIndices.push(index);
          }
        } else if (!secrets[meetings[todoIndex][0]] && secrets[meetings[todoIndex][1]]) {
          secrets[meetings[todoIndex][0]] = true;
          for (int index : getIndexList(indexLists, times, meetings[todoIndex][0], time)) {
            todoIndices.push(index);
          }
        }
      }

      beginIndex = endIndex + 1;
    }

    return IntStream.range(0, secrets.length).filter(i -> secrets[i]).boxed().toList();
  }

  List<Integer> getIndexList(List<Integer>[] indexLists, int[] times, int person, int time) {
    if (times[person] != time) {
      times[person] = time;
      indexLists[person] = new ArrayList<>();
    }

    return indexLists[person];
  }
}
