import java.util.Arrays;

class Solution {
  public int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);
    Arrays.sort(s);

    int result = 0;
    int sIndex = 0;
    for (int gi : g) {
      while (sIndex != s.length && s[sIndex] < gi) {
        ++sIndex;
      }
      if (sIndex != s.length) {
        ++result;
        ++sIndex;
      }
    }

    return result;
  }
}
