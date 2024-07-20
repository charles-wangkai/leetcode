import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
    int row = rowSum.length;
    int col = colSum.length;

    Comparator<Element> elementComparator = Comparator.comparing(element -> element.sum);

    PriorityQueue<Element> rowElements = new PriorityQueue<>(elementComparator);
    for (int r = 0; r < row; ++r) {
      if (rowSum[r] != 0) {
        rowElements.offer(new Element(r, rowSum[r]));
      }
    }
    PriorityQueue<Element> colElements = new PriorityQueue<>(elementComparator);
    for (int c = 0; c < col; ++c) {
      if (colSum[c] != 0) {
        colElements.offer(new Element(c, colSum[c]));
      }
    }

    int[][] result = new int[row][col];
    while (!rowElements.isEmpty()) {
      Element rowElement = rowElements.poll();
      Element colElement = colElements.poll();

      int delta = Math.min(rowElement.sum, colElement.sum);
      result[rowElement.index][colElement.index] += delta;

      rowElement.sum -= delta;
      if (rowElement.sum != 0) {
        rowElements.offer(rowElement);
      }

      colElement.sum -= delta;
      if (colElement.sum != 0) {
        colElements.offer(colElement);
      }
    }

    return result;
  }
}

class Element {
  int index;
  int sum;

  Element(int index, int sum) {
    this.index = index;
    this.sum = sum;
  }
}
