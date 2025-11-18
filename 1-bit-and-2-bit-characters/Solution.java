class Solution {
  public boolean isOneBitCharacter(int[] bits) {
    int index = 0;
    while (true) {
      if (index == bits.length - 1) {
        return true;
      }
      if (index == bits.length) {
        return false;
      }

      index += (bits[index] == 0) ? 1 : 2;
    }
  }
}
