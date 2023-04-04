import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

class Solution {
  public int[] minReverseOperations(int n, int p, int[] banned, int k) {
    NavigableSet<Integer> restEvenIndices = new TreeSet<>();
    NavigableSet<Integer> restOddIndices = new TreeSet<>();
    for (int i = 0; i < n; ++i) {
      ((i % 2 == 0) ? restEvenIndices : restOddIndices).add(i);
    }
    for (int b : banned) {
      ((b % 2 == 0) ? restEvenIndices : restOddIndices).remove(b);
    }

    int[] result = new int[n];
    Arrays.fill(result, -1);
    result[p] = 0;
    ((p % 2 == 0) ? restEvenIndices : restOddIndices).remove(p);
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(p);
    while (!queue.isEmpty()) {
      int head = queue.poll();
      Range range = computeRange(n, k, head);
      if (range != null) {
        NavigableSet<Integer> nextIndices =
            ((range.beginIndex() % 2 == 0) ? restEvenIndices : restOddIndices)
                .subSet(range.beginIndex(), true, range.endIndex(), true);
        for (int nextIndex : nextIndices) {
          result[nextIndex] = result[head] + 1;
          queue.offer(nextIndex);
        }

        nextIndices.clear();
      }
    }

    return result;
  }

  Range computeRange(int n, int k, int index) {
    int beginIndex = index - k + 1;
    beginIndex = Math.max(beginIndex, Math.floorMod(beginIndex, 2));
    beginIndex = Math.max(beginIndex, Math.max(k - 1, index + beginIndex) - index);

    int endIndex = index + k - 1;
    endIndex = Math.min(endIndex, n - 1 - Math.floorMod(endIndex - (n - 1), 2));
    endIndex = Math.min(endIndex, Math.min((n - k) + (n - 1), index + endIndex) - index);

    return (beginIndex <= endIndex) ? new Range(beginIndex, endIndex) : null;
  }
}

record Range(int beginIndex, int endIndex) {}
