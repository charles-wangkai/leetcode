import java.util.SortedMap;
import java.util.TreeMap;

class BookMyShow {
  int n;
  int m;
  int[] segmentTree;
  long[] A;
  SortedMap<Integer, Integer> rowToRest = new TreeMap<>();

  public BookMyShow(int n, int m) {
    this.n = n;
    this.m = m;

    segmentTree = new int[Integer.highestOneBit(n) * 4];
    A = new long[1 + Integer.highestOneBit(n) * 2];
    for (int r = 0; r < n; ++r) {
      updateSegmentTree(r, m, 0, n - 1, 0);
      add(r, m);
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
    add(row, -k);

    return new int[] {row, m - rowToRest.getOrDefault(row, 0) - k};
  }

  public boolean scatter(int k, int maxRow) {
    if (prefixSum(maxRow) < k) {
      return false;
    }

    while (k != 0) {
      int row = rowToRest.firstKey();
      int delta = Math.min(rowToRest.get(row), k);

      rowToRest.put(row, rowToRest.get(row) - delta);
      rowToRest.remove(row, 0);

      updateSegmentTree(row, rowToRest.getOrDefault(row, 0), 0, n - 1, 0);
      add(row, -delta);

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

  int LSB(int i) {
    return i & -i;
  }

  long prefixSum(int i) {
    long sum = A[0];
    for (; i != 0; i -= LSB(i)) sum += A[i];
    return sum;
  }

  void add(int i, int delta) {
    if (i == 0) {
      A[0] += delta;
      return;
    }
    for (; i < A.length; i += LSB(i)) A[i] += delta;
  }
}

// Your BookMyShow object will be instantiated and called as such:
// BookMyShow obj = new BookMyShow(n, m);
// int[] param_1 = obj.gather(k,maxRow);
// boolean param_2 = obj.scatter(k,maxRow);
