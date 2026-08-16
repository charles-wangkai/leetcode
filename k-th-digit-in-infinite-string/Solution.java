class Solution {
  public int kthDigit(long k) {
    --k;

    int length = 1;
    long valueNum = 9;
    long firstBlock = 0;
    while (k >= valueNum * length) {
      k -= valueNum * length;

      ++length;
      valueNum *= 10;

      if (firstBlock == 0) {
        firstBlock = 1;
      } else {
        firstBlock *= 10;
      }
    }

    long block = firstBlock + k / length / 10;
    int seq = (int) (k / length % 10);
    int index = (int) (k % length);

    if (index == length - 1) {
      if (block == 0) {
        return seq + 1;
      }

      return (block % 2 == 0) ? seq : (9 - seq);
    }

    return String.valueOf(block).charAt(index) - '0';
  }
}