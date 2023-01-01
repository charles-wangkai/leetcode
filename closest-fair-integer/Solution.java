class Solution {
  public int closestFair(int n) {
    String s = String.valueOf(n);
    int lastIndex = -1;
    if (s.length() % 2 == 0) {
      int evenCount = 0;
      int oddCount = 0;
      for (int i = 0; i < s.length(); ++i) {
        if ((s.charAt(i) - '0') % 2 == 0) {
          ++evenCount;
        } else {
          ++oddCount;
        }

        if (Math.abs(evenCount - oddCount) > s.length() - 1 - i) {
          lastIndex = i;
        }
      }
    } else {
      lastIndex = 0;
    }

    if (lastIndex == -1) {
      return n;
    }

    int placeValue = Integer.parseInt("1" + "0".repeat(s.length() - 1 - lastIndex));

    return closestFair(n / placeValue * placeValue + placeValue);
  }
}
