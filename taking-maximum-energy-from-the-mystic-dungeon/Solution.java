class Solution {
  public int maximumEnergy(int[] energy, int k) {
    int result = Integer.MIN_VALUE;
    for (int i = energy.length - 1; i >= energy.length - k; --i) {
      int sum = 0;
      for (int j = i; j >= 0; j -= k) {
        sum += energy[j];
        result = Math.max(result, sum);
      }
    }

    return result;
  }
}