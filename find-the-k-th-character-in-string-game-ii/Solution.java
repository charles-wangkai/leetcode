class Solution {
  public char kthCharacter(long k, int[] operations) {
    long length = 1;
    int operationNum = 0;
    while (length < k) {
      length <<= 1;
      ++operationNum;
    }

    return find(operations, k, length, operationNum);
  }

  char find(int[] operations, long k, long length, int operationNum) {
    if (k == 1) {
      return 'a';
    }

    long half = length / 2;
    if (k <= half) {
      return find(operations, k, half, operationNum - 1);
    }

    return (char)
        ((find(operations, k - half, half, operationNum - 1) - 'a' + operations[operationNum - 1])
                % 26
            + 'a');
  }
}