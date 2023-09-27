class Solution {
  public String decodeAtIndex(String s, int k) {
    long[] lengths = buildLengths(s);

    return String.valueOf(search(s, lengths, s.length() - 1, k));
  }

  char search(String s, long[] lengths, int index, long sequence) {
    char c = s.charAt(index);
    if (Character.isLetter(c)) {
      if (sequence == lengths[index]) {
        return c;
      }

      return search(s, lengths, index - 1, sequence);
    }

    return search(s, lengths, index - 1, (sequence - 1) % lengths[index - 1] + 1);
  }

  long[] buildLengths(String s) {
    long[] lengths = new long[s.length()];
    long length = 0;
    for (int i = 0; i < lengths.length; ++i) {
      if (Character.isLetter(s.charAt(i))) {
        ++length;
      } else {
        length *= s.charAt(i) - '0';
      }

      lengths[i] = length;
    }

    return lengths;
  }
}
