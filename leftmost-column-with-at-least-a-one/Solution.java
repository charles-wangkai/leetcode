import java.util.List;
import java.util.stream.IntStream;

// This is the BinaryMatrix's API interface.
// You should not implement it, or speculate about its implementation
interface BinaryMatrix {
  public int get(int row, int col);

  public List<Integer> dimensions();
}

class Solution {
  public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
    List<Integer> d = binaryMatrix.dimensions();
    int row = d.get(0);
    int col = d.get(1);

    return IntStream.range(0, row)
        .map(r -> findLeftIndex(binaryMatrix, r, col))
        .filter(x -> x != -1)
        .min()
        .orElse(-1);
  }

  int findLeftIndex(BinaryMatrix binaryMatrix, int r, int col) {
    int result = -1;
    int lower = 0;
    int upper = col - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (binaryMatrix.get(r, middle) == 1) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }
}
