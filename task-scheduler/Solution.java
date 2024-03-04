class Solution {
  static final int ALPHABET_SIZE = 26;

  public int leastInterval(char[] tasks, int n) {
    int[] counts = new int[ALPHABET_SIZE];
    for (char task : tasks) {
      ++counts[task - 'A'];
    }

    int result = 0;
    int[] waits = new int[ALPHABET_SIZE];
    int rest = tasks.length;
    while (rest != 0) {
      int chosen = -1;
      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        if (waits[i] != 0) {
          --waits[i];
        } else if (counts[i] != 0 && (chosen == -1 || counts[i] > counts[chosen])) {
          chosen = i;
        }
      }

      if (chosen != -1) {
        --counts[chosen];
        waits[chosen] = n;
        --rest;
      }

      ++result;
    }

    return result;
  }
}
