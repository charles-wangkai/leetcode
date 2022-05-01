class Solution {
  public boolean backspaceCompare(String s, String t) {
    int sIndex = s.length() - 1;
    int sDeleteCount = 0;
    int tIndex = t.length() - 1;
    int tDeleteCount = 0;
    while (true) {
      while (sIndex >= 0 && (s.charAt(sIndex) == '#' || sDeleteCount != 0)) {
        if (s.charAt(sIndex) == '#') {
          ++sDeleteCount;
        } else {
          --sDeleteCount;
        }

        --sIndex;
      }

      while (tIndex >= 0 && (t.charAt(tIndex) == '#' || tDeleteCount != 0)) {
        if (t.charAt(tIndex) == '#') {
          ++tDeleteCount;
        } else {
          --tDeleteCount;
        }

        --tIndex;
      }

      if (sIndex == -1 && tIndex == -1) {
        return true;
      }
      if (sIndex == -1 || tIndex == -1 || s.charAt(sIndex) != t.charAt(tIndex)) {
        return false;
      }

      --sIndex;
      --tIndex;
    }
  }
}
