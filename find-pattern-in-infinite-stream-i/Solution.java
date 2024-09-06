// Definition for an infinite stream.

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class InfiniteStream {
  public InfiniteStream(int[] bits) {
    throw new UnsupportedOperationException();
  }

  public int next() {
    throw new UnsupportedOperationException();
  }
}

class Solution {
  public int findPattern(InfiniteStream infiniteStream, int[] pattern) {
    List<Integer> received = new ArrayList<>();
    while (true) {
      received.add(infiniteStream.next());
      if (isMatching(received, pattern)) {
        return received.size() - pattern.length;
      }
    }
  }

  boolean isMatching(List<Integer> received, int[] pattern) {
    return received.size() >= pattern.length
        && IntStream.range(0, pattern.length)
            .allMatch(i -> received.get(received.size() - pattern.length + i) == pattern[i]);
  }
}