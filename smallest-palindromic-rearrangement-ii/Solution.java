class Solution {
  public String smallestPalindrome(String s, int k) {
    String half = findKthPermutation(s.substring(0, s.length() / 2), k);
    if (half == null) {
      return "";
    }

    return "%s%s%s"
        .formatted(
            half,
            (s.length() % 2 == 0) ? "" : String.valueOf(s.charAt(s.length() / 2)),
            new StringBuilder(half).reverse().toString());
  }

  String findKthPermutation(String str, int k) {
    int[] counts = new int[26];
    for (char c : str.toCharArray()) {
      ++counts[c - 'a'];
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < str.length(); ++i) {
      int index = 0;
      while (true) {
        if (index == counts.length) {
          return null;
        }

        if (counts[index] != 0) {
          --counts[index];
          int wayNum = computeWayNum(k, str.length() - i - 1, counts);
          ++counts[index];

          if (wayNum >= k) {
            result.append((char) (index + 'a'));
            --counts[index];

            break;
          } else {
            k -= wayNum;
          }
        }

        ++index;
      }
    }

    return result.toString();
  }

  int computeWayNum(int k, int length, int[] counts) {
    long result = 1;
    for (int count : counts) {
      int r = Math.min(count, length - count);
      for (int i = 0; i < r; ++i) {
        result = result * (length - i) / (i + 1);
        if (result >= k) {
          return Integer.MAX_VALUE;
        }
      }

      length -= count;
    }

    return (int) result;
  }
}