import java.util.Arrays;

class Solution {
  public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
    int hGap = computeMaxGap(hBars);
    int vGap = computeMaxGap(vBars);

    return Math.min(hGap, vGap) * Math.min(hGap, vGap);
  }

  int computeMaxGap(int[] bars) {
    Arrays.sort(bars);

    int result = 1;
    int count = 0;
    for (int i = 0; i < bars.length; ++i) {
      if (i != 0 && bars[i] == bars[i - 1] + 1) {
        ++count;
      } else {
        count = 1;
      }

      result = Math.max(result, count + 1);
    }

    return result;
  }
}