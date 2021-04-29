class Solution {
  public String nextPalindrome(String num) {
    char[] half = num.substring(0, num.length() / 2).toCharArray();
    int leftIndex = half.length - 2;
    while (leftIndex >= 0 && half[leftIndex] >= half[leftIndex + 1]) {
      --leftIndex;
    }
    if (leftIndex < 0) {
      return "";
    }

    int rightIndex = leftIndex + 1;
    while (rightIndex + 1 != half.length && half[rightIndex + 1] > half[leftIndex]) {
      ++rightIndex;
    }

    swap(half, leftIndex, rightIndex);
    for (int i = leftIndex + 1, j = half.length - 1; i < j; ++i, --j) {
      swap(half, i, j);
    }

    return String.format(
        "%s%s%s",
        new String(half),
        (num.length() % 2 == 0) ? "" : String.valueOf(num.charAt(num.length() / 2)),
        new StringBuilder(new String(half)).reverse().toString());
  }

  void swap(char[] chs, int index1, int index2) {
    char temp = chs[index1];
    chs[index1] = chs[index2];
    chs[index2] = temp;
  }
}
