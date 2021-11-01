import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final int SIZE = 8;
  static final Map<String, int[]> PIECE_TO_R_OFFSETS =
      Map.ofEntries(
          entry("rook", new int[] {0, 0, -1, 1}),
          entry("queen", new int[] {0, 0, -1, 1, -1, 1, -1, 1}),
          entry("bishop", new int[] {-1, 1, -1, 1}));
  static final Map<String, int[]> PIECE_TO_C_OFFSETS =
      Map.ofEntries(
          entry("rook", new int[] {-1, 1, 0, 0}),
          entry("queen", new int[] {-1, 1, 0, 0, -1, 1, 1, -1}),
          entry("bishop", new int[] {-1, 1, 1, -1}));

  public int countCombinations(String[] pieces, int[][] positions) {
    @SuppressWarnings("unchecked")
    List<Move>[] moveLists =
        IntStream.range(0, pieces.length)
            .mapToObj(i -> buildMoves(pieces[i], positions[i]))
            .toArray(List[]::new);

    return search(moveLists, new Move[pieces.length], 0);
  }

  int search(List<Move>[] moveLists, Move[] moves, int index) {
    if (index == moves.length) {
      return check(moves) ? 1 : 0;
    }

    int result = 0;
    for (Move move : moveLists[index]) {
      moves[index] = move;
      result += search(moveLists, moves, index + 1);
    }

    return result;
  }

  boolean check(Move[] moves) {
    int[] toRs = new int[moves.length];
    int[] toCs = new int[moves.length];
    int maxStep = Arrays.stream(moves).mapToInt(move -> move.step).max().getAsInt();
    for (int i = 1; i <= maxStep; ++i) {
      for (int j = 0; j < moves.length; ++j) {
        toRs[j] = moves[j].fromR + Math.min(i, moves[j].step) * moves[j].offsetR;
        toCs[j] = moves[j].fromC + Math.min(i, moves[j].step) * moves[j].offsetC;

        for (int k = 0; k < j; ++k) {
          if (toRs[j] == toRs[k] && toCs[j] == toCs[k]) {
            return false;
          }
        }
      }
    }

    return true;
  }

  List<Move> buildMoves(String piece, int[] position) {
    List<Move> result = new ArrayList<>();
    result.add(new Move(position[0], position[1], 0, 0, 0));
    for (int i = 0; i < PIECE_TO_R_OFFSETS.get(piece).length; ++i) {
      int offsetR = PIECE_TO_R_OFFSETS.get(piece)[i];
      int offsetC = PIECE_TO_C_OFFSETS.get(piece)[i];
      for (int step = 1; ; ++step) {
        int toR = position[0] + offsetR * step;
        int toC = position[1] + offsetC * step;
        if (!(toR >= 1 && toR <= SIZE && toC >= 1 && toC <= SIZE)) {
          break;
        }

        result.add(new Move(position[0], position[1], offsetR, offsetC, step));
      }
    }

    return result;
  }
}

class Move {
  int fromR;
  int fromC;
  int offsetR;
  int offsetC;
  int step;

  Move(int fromR, int fromC, int offsetR, int offsetC, int step) {
    this.fromR = fromR;
    this.fromC = fromC;
    this.offsetR = offsetR;
    this.offsetC = offsetC;
    this.step = step;
  }
}
