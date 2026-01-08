import java.util.SortedMap;
import java.util.TreeMap;

class BookMyShow {
  int n;
  int m;
  int[] segmentTree;
  FenwickTree fenwickTree;
  SortedMap<Integer, Integer> rowToRest = new TreeMap<>();

  public BookMyShow(int n, int m) {
    this.n = n;
    this.m = m;

    segmentTree = new int[Integer.highestOneBit(n) * 4];
    fenwickTree = new FenwickTree(n);
    for (int r = 0; r < n; ++r) {
      updateSegmentTree(r, m, 0, n - 1, 0);
      fenwickTree.add(r + 1, m);
    }

    for (int r = 0; r < n; ++r) {
      rowToRest.put(r, m);
    }
  }

  public int[] gather(int k, int maxRow) {
    int row = -1;
    int lower = 0;
    int upper = maxRow;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (querySegmentTree(0, middle, 0, n - 1, 0) >= k) {
        row = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    if (row == -1) {
      return new int[0];
    }

    rowToRest.put(row, rowToRest.get(row) - k);
    rowToRest.remove(row, 0);

    updateSegmentTree(row, rowToRest.getOrDefault(row, 0), 0, n - 1, 0);
    fenwickTree.add(row + 1, -k);

    return new int[] {row, m - rowToRest.getOrDefault(row, 0) - k};
  }

  public boolean scatter(int k, int maxRow) {
    if (fenwickTree.computePrefixSum(maxRow + 1) < k) {
      return false;
    }

    while (k != 0) {
      int row = rowToRest.firstKey();
      int delta = Math.min(rowToRest.get(row), k);

      rowToRest.put(row, rowToRest.get(row) - delta);
      rowToRest.remove(row, 0);

      updateSegmentTree(row, rowToRest.getOrDefault(row, 0), 0, n - 1, 0);
      fenwickTree.add(row + 1, -delta);

      k -= delta;
    }

    return true;
  }

  void updateSegmentTree(int index, int newValue, int beginIndex, int endIndex, int node) {
    if (beginIndex == endIndex) {
      segmentTree[node] = newValue;

      return;
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    if (index <= middleIndex) {
      updateSegmentTree(index, newValue, beginIndex, middleIndex, node * 2 + 1);
    } else {
      updateSegmentTree(index, newValue, middleIndex + 1, endIndex, node * 2 + 2);
    }

    segmentTree[node] = Math.max(segmentTree[node * 2 + 1], segmentTree[node * 2 + 2]);
  }

  int querySegmentTree(int from, int to, int beginIndex, int endIndex, int node) {
    if (from == beginIndex && to == endIndex) {
      return segmentTree[node];
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    int result = 0;
    if (from <= middleIndex) {
      result =
          Math.max(
              result,
              querySegmentTree(
                  from, Math.min(middleIndex, to), beginIndex, middleIndex, node * 2 + 1));
    }
    if (to >= middleIndex + 1) {
      result =
          Math.max(
              result,
              querySegmentTree(
                  Math.max(middleIndex + 1, from), to, middleIndex + 1, endIndex, node * 2 + 2));
    }

    return result;
  }
}

class FenwickTree {
  long[] a;

  FenwickTree(int size) {
    a = new long[Integer.highestOneBit(size) * 2 + 1];
  }

  void add(int pos, int delta) {
    while (pos < a.length) {
      a[pos] += delta;
      pos += pos & -pos;
    }
  }

  long computePrefixSum(int pos) {
    long result = 0;
    while (pos != 0) {
      result += a[pos];
      pos -= pos & -pos;
    }

    return result;
  }
}
