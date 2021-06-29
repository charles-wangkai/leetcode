class Solution {
  static final int ALPHABET_SIZE = 10;

  public long wonderfulSubstrings(String word) {
    long result = 0;
    int[] counts = new int[1 << ALPHABET_SIZE];
    counts[0] = 1;
    int mask = 0;
    for (char ch : word.toCharArray()) {
      mask ^= 1 << (ch - 'a');

      result += counts[mask];
      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        result += counts[mask ^ (1 << i)];
      }

      ++counts[mask];
    }

    return result;
  }
}
