class Solution {
  public int largestInteger(int num) {
    char[] digits = String.valueOf(num).toCharArray();
    while (true) {
      boolean changed = false;
      for (int i = 0; i < digits.length; ++i) {
        for (int j = i + 1; j < digits.length; ++j) {
          if (digits[i] % 2 == digits[j] % 2 && digits[i] < digits[j]) {
            char temp = digits[i];
            digits[i] = digits[j];
            digits[j] = temp;

            changed = true;
          }
        }
      }

      if (!changed) {
        break;
      }
    }

    return Integer.parseInt(new String(digits));
  }
}