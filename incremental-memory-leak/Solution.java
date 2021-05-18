class Solution {
  public int[] memLeak(int memory1, int memory2) {
    for (int i = 1; ; ++i) {
      if (Math.max(memory1, memory2) < i) {
        return new int[] {i, memory1, memory2};
      }

      if (memory1 >= memory2) {
        memory1 -= i;
      } else {
        memory2 -= i;
      }
    }
  }
}
