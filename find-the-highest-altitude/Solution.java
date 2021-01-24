class Solution {
  public int largestAltitude(int[] gain) {
    int result = 0;
    int altitude = 0;
    for (int g : gain) {
      altitude += g;
      result = Math.max(result, altitude);
    }

    return result;
  }
}
