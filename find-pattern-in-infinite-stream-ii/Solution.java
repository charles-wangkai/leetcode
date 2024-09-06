// Definition for an infinite stream.

import java.util.ArrayDeque;
import java.util.Queue;

class InfiniteStream {
  public InfiniteStream(int[] bits) {
    throw new UnsupportedOperationException();
  }

  public int next() {
    throw new UnsupportedOperationException();
  }
}

class Solution {
  static final int BASE = 31;

  public int findPattern(InfiniteStream infiniteStream, int[] pattern) {
    long targetHash = 0;
    for (int b : pattern) {
      targetHash = targetHash * BASE + b;
    }

    long placeValue = 1;
    for (int i = 0; i < pattern.length; ++i) {
      placeValue *= BASE;
    }

    Queue<Integer> received = new ArrayDeque<>();
    long hash = 0;
    for (int i = 0; ; ++i) {
      int b = infiniteStream.next();
      received.offer(b);
      hash = hash * BASE + b;
      if (received.size() == pattern.length + 1) {
        hash -= received.poll() * placeValue;
      }

      if (received.size() == pattern.length && hash == targetHash) {
        return i + 1 - pattern.length;
      }
    }
  }
}