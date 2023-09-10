class Solution {
  public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
    int minTime = Math.max(Math.abs(sx - fx), Math.abs(sy - fy));

    return minTime <= t && !(minTime == 0 && t == 1);
  }
}
