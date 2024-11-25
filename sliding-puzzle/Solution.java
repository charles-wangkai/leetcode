import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int slidingPuzzle(int[][] board) {
    String state = convertToState(board);

    Map<String, Integer> stateToStep = new HashMap<>();
    stateToStep.put(state, 0);

    Queue<String> states = new ArrayDeque<>();
    states.offer(state);

    while (!states.isEmpty()) {
      String head = states.poll();
      if (head.equals("123450")) {
        return stateToStep.get(head);
      }

      for (String nextState : move(head)) {
        if (!stateToStep.containsKey(nextState)) {
          stateToStep.put(nextState, stateToStep.get(head) + 1);
          states.offer(nextState);
        }
      }
    }

    return -1;
  }

  List<String> move(String state) {
    List<String> nextStates = new ArrayList<>();
    int index = state.indexOf('0');
    int r = index / 3;
    int c = index % 3;

    nextStates.add(swap(state, convertToIndex(0, c), convertToIndex(1, c)));
    if (c != 0) {
      nextStates.add(swap(state, convertToIndex(r, c), convertToIndex(r, c - 1)));
    }
    if (c != 2) {
      nextStates.add(swap(state, convertToIndex(r, c), convertToIndex(r, c + 1)));
    }

    return nextStates;
  }

  int convertToIndex(int r, int c) {
    return r * 3 + c;
  }

  String swap(String s, int index1, int index2) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      int index;
      if (i == index1) {
        index = index2;
      } else if (i == index2) {
        index = index1;
      } else {
        index = i;
      }

      result.append(s.charAt(index));
    }

    return result.toString();
  }

  String convertToState(int[][] board) {
    StringBuilder result = new StringBuilder();
    for (int[] line : board) {
      for (int x : line) {
        result.append(x);
      }
    }

    return result.toString();
  }
}
