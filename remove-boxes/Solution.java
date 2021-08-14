class Solution {
  static final int LIMIT = 100;

  int[][][] cache;

  public int removeBoxes(int[] boxes) {
    cache = new int[LIMIT][LIMIT][LIMIT];

    return search(boxes, 0, boxes.length - 1, 0);
  }

  int search(int[] boxes, int left, int right, int append) {
    if (left > right) {
      return 0;
    }

    if (cache[left][right][append] == 0) {
      int result = search(boxes, left, right - 1, 0) + (append + 1) * (append + 1);
      for (int i = left; i < right; ++i) {
        if (boxes[i] == boxes[right]) {
          result =
              Math.max(
                  result, search(boxes, left, i, append + 1) + search(boxes, i + 1, right - 1, 0));
        }
      }

      cache[left][right][append] = result;
    }

    return cache[left][right][append];
  }
}
