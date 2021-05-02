class Solution {
  public int getMinSwaps(String num, int k) {
    char[] digits = num.toCharArray();
    for (int i = 0; i < k; ++i) {
      int leftIndex = digits.length - 2;
      while (digits[leftIndex] >= digits[leftIndex + 1]) {
        --leftIndex;
      }

      int rightIndex = leftIndex + 1;
      while (rightIndex + 1 != digits.length && digits[rightIndex + 1] > digits[leftIndex]) {
        ++rightIndex;
      }

      swap(digits, leftIndex, rightIndex);

      for (int p = leftIndex + 1, q = digits.length - 1; p < q; ++p, --q) {
        swap(digits, p, q);
      }
    }

    int result = 0;
    for (int i = 0; i < num.length(); ++i) {
      int index = i;
      while (digits[index] != num.charAt(i)) {
        ++index;
      }

      for (int j = index - 1; j >= i; --j) {
        swap(digits, j, j + 1);
        ++result;
      }
    }

    return result;
  }

  void swap(char[] digits, int index1, int index2) {
    char temp = digits[index1];
    digits[index1] = digits[index2];
    digits[index2] = temp;
  }
}
