import java.util.List;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int maxLength(List<String> arr) {
    int result = -1;
    for (int mask = 0; mask < 1 << arr.size(); ++mask) {
      result = Math.max(result, computeSeenCount(arr, mask));
    }

    return result;
  }

  int computeSeenCount(List<String> arr, int mask) {
    boolean[] seens = new boolean[ALPHABET_SIZE];
    int result = 0;
    for (int i = 0; i < arr.size(); ++i) {
      if (((mask >> i) & 1) == 1) {
        for (char c : arr.get(i).toCharArray()) {
          if (seens[c - 'a']) {
            return -1;
          } else {
            seens[c - 'a'] = true;
            ++result;
          }
        }
      }
    }

    return result;
  }
}
