// https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals/solutions/6232381/python-dp/

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[] maximumWeight(List<List<Integer>> intervals) {
    int[] sortedIndices =
        IntStream.range(0, intervals.size())
            .boxed()
            .sorted(Comparator.comparing(i -> intervals.get(i).get(0)))
            .mapToInt(Integer::intValue)
            .toArray();

    return search(new HashMap<>(), intervals, sortedIndices, 0, 4).indices();
  }

  Outcome search(
      Map<State, Outcome> cache,
      List<List<Integer>> intervals,
      int[] sortedIndices,
      int index,
      int rest) {
    if (index == intervals.size() || rest == 0) {
      return new Outcome(0, new int[0]);
    }

    State state = new State(index, rest);
    if (!cache.containsKey(state)) {
      Outcome skipOutcome = search(cache, intervals, sortedIndices, index + 1, rest);

      int nextIndex =
          findNextIndex(intervals, sortedIndices, intervals.get(sortedIndices[index]).get(1) + 1);
      Outcome subOutcome = search(cache, intervals, sortedIndices, nextIndex, rest - 1);
      Outcome pickOutcome =
          new Outcome(
              intervals.get(sortedIndices[index]).get(2) + subOutcome.weightSum(),
              IntStream.concat(
                      IntStream.of(sortedIndices[index]), Arrays.stream(subOutcome.indices()))
                  .sorted()
                  .toArray());

      cache.put(state, merge(skipOutcome, pickOutcome));
    }

    return cache.get(state);
  }

  int findNextIndex(List<List<Integer>> intervals, int[] sortedIndices, int minLeft) {
    int result = sortedIndices.length;
    int lower = 0;
    int upper = sortedIndices.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (intervals.get(sortedIndices[middle]).get(0) >= minLeft) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  Outcome merge(Outcome o1, Outcome o2) {
    if (o1.weightSum() != o2.weightSum()) {
      return (o1.weightSum() > o2.weightSum()) ? o1 : o2;
    }

    for (int i = 0; ; ++i) {
      if (i == o1.indices().length) {
        return o1;
      }
      if (i == o2.indices().length) {
        return o2;
      }

      if (o1.indices()[i] != o2.indices()[i]) {
        return (o1.indices()[i] < o2.indices()[i]) ? o1 : o2;
      }
    }
  }
}

record State(int index, int rest) {}

record Outcome(long weightSum, int[] indices) {}
