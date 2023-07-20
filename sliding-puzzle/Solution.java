import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
  public int slidingPuzzle(int[][] board) {
    Map<String, Integer> state2step = new HashMap<>();
    String state = convertToState(board);
    state2step.put(state, 0);
    Queue<String> states = new LinkedList<>();
    states.offer(state);
    while (!states.isEmpty()) {
      String head = states.poll();
      int step = state2step.get(head);

      if (head.equals("123450")) {
        return step;
      }

      for (String next : move(head)) {
        if (!state2step.containsKey(next)) {
          state2step.put(next, step + 1);
          states.offer(next);
        }
      }
    }
    return -1;
  }

  List<String> move(String state) {
    List<String> nextStates = new ArrayList<>();
    int index0 = state.indexOf('0');
    int row0 = index0 / 3;
    int col0 = index0 % 3;

    nextStates.add(swap(state, convertToIndex(0, col0), convertToIndex(1, col0)));
    if (col0 > 0) {
      nextStates.add(swap(state, convertToIndex(row0, col0), convertToIndex(row0, col0 - 1)));
    }
    if (col0 < 2) {
      nextStates.add(swap(state, convertToIndex(row0, col0), convertToIndex(row0, col0 + 1)));
    }

    return nextStates;
  }

  int convertToIndex(int row, int col) {
    return row * 3 + col;
  }

  String swap(String s, int index1, int index2) {
    StringBuilder sb = new StringBuilder(s);
    char temp = sb.charAt(index1);
    sb.setCharAt(index1, sb.charAt(index2));
    sb.setCharAt(index2, temp);
    return sb.toString();
  }

  String convertToState(int[][] board) {
    StringBuilder state = new StringBuilder();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        state.append(board[i][j]);
      }
    }
    return state.toString();
  }
}
