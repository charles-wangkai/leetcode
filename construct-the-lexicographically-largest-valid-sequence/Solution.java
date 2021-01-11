class Solution {
  public int[] constructDistancedSequence(int n) {
    return search(new int[n * 2 - 1], new boolean[n + 1], 0);
  }

  int[] search(int[] sequence, boolean[] used, int index) {
    if (index == sequence.length) {
      return sequence;
    }
    if (sequence[index] != 0) {
      return search(sequence, used, index + 1);
    }

    for (int i = used.length - 1; i >= 1; --i) {
      if (!used[i] && (i == 1 || (index + i < sequence.length && sequence[index + i] == 0))) {
        used[i] = true;
        sequence[index] = i;
        if (i != 1) {
          sequence[index + i] = i;
        }

        int[] subResult = search(sequence, used, index + 1);
        if (subResult != null) {
          return subResult;
        }

        used[i] = false;
        sequence[index] = 0;
        if (i != 1) {
          sequence[index + i] = 0;
        }
      }
    }

    return null;
  }
}
