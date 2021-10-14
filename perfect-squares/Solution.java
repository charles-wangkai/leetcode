import java.util.HashMap;
import java.util.Map;

class Solution {
  Map<Integer, Integer> cache = new HashMap<>();

  public int numSquares(int n) {
    return search(n, computeSquareNumLimit(n));
  }

  int computeSquareNumLimit(int n) {
    int squareNumLimit = 0;
    while (n != 0) {
      int root = (int) Math.round(Math.sqrt(n));
      if (root * root > n) {
        --root;
      }
      n -= root * root;
      ++squareNumLimit;
    }

    return squareNumLimit;
  }

  Integer search(int n, int squareNumLimit) {
    if (n == 0) {
      return 0;
    }
    if (squareNumLimit == 0) {
      return null;
    }

    if (cache.containsKey(n)) {
      int result = cache.get(n);

      return (result <= squareNumLimit) ? result : null;
    }

    Integer result = null;
    for (int i = 1; i * i <= n; ++i) {
      Integer subResult = search(n - i * i, squareNumLimit - 1);

      if (subResult != null && (result == null || subResult + 1 < result)) {
        result = subResult + 1;
        squareNumLimit = subResult;
      }
    }

    if (result != null) {
      cache.put(n, result);
    }

    return result;
  }
}
