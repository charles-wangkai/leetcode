class Solution {
  public boolean canReach(String s, int minJump, int maxJump) {
    int[] prefixSums = new int[s.length() + 1];
    boolean[] reaches = new boolean[s.length()];
    reaches[0] = true;
    prefixSums[1] = 1;
    for (int i = 1; i < reaches.length; ++i) {
      if (s.charAt(i) == '0' && hasPrevReach(prefixSums, i - maxJump, i - minJump)) {
        reaches[i] = true;
      }

      prefixSums[i + 1] = prefixSums[i] + (reaches[i] ? 1 : 0);
    }

    return reaches[reaches.length - 1];
  }

  boolean hasPrevReach(int[] prefixSums, int minIndex, int maxIndex) {
    if (maxIndex < 0) {
      return false;
    }

    minIndex = Math.max(0, minIndex);

    return prefixSums[maxIndex + 1] - prefixSums[minIndex] != 0;
  }
}
