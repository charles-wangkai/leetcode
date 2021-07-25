class Solution {
  public int getLucky(String s, int k) {
    int result =
        s.chars()
            .map(
                ch -> {
                  int pos = ch - 'a' + 1;

                  return pos / 10 + pos % 10;
                })
            .sum();

    for (int i = 0; i < k - 1; ++i) {
      result = String.valueOf(result).chars().map(ch -> ch - '0').sum();
    }

    return result;
  }
}
