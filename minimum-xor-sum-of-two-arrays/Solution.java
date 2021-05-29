class Solution {
  int minXorSum;

  public int minimumXORSum(int[] nums1, int[] nums2) {
    int n = nums1.length;

    int leftSize = n / 2;
    int rightSize = n - leftSize;

    int[] left1 = new int[leftSize];
    for (int i = 0; i < left1.length; ++i) {
      left1[i] = nums1[i];
    }
    int[] right1 = new int[rightSize];
    for (int i = 0; i < right1.length; ++i) {
      right1[i] = nums1[leftSize + i];
    }

    int[] left2 = new int[leftSize];
    int[] right2 = new int[rightSize];
    int result = Integer.MAX_VALUE;
    for (int code = 0; code < 1 << n; ++code) {
      if (Integer.bitCount(code) == leftSize) {
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < n; ++i) {
          if ((code & (1 << i)) != 0) {
            left2[leftIndex] = nums2[i];
            ++leftIndex;
          } else {
            right2[rightIndex] = nums2[i];
            ++rightIndex;
          }
        }

        result =
            Math.min(result, computeMinXorSum(left1, left2) + computeMinXorSum(right1, right2));
      }
    }

    return result;
  }

  int computeMinXorSum(int[] values1, int[] values2) {
    minXorSum = Integer.MAX_VALUE;

    search(values1, values2, 0, 0);

    return minXorSum;
  }

  void search(int[] values1, int[] values2, int index, int xorSum) {
    int n = values1.length;

    if (index == n) {
      minXorSum = Math.min(minXorSum, xorSum);
    }

    for (int i = index; i < n; ++i) {
      swap(values2, i, index);

      int nextXorSum = xorSum + (values1[index] ^ values2[index]);
      if (nextXorSum < minXorSum) {
        search(values1, values2, index + 1, nextXorSum);
      }

      swap(values2, i, index);
    }
  }

  void swap(int[] values, int index1, int index2) {
    int temp = values[index1];
    values[index1] = values[index2];
    values[index2] = temp;
  }
}
