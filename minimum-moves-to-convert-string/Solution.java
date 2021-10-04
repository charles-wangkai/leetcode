class Solution {
  public int minimumMoves(String s) {
    int result = 0;
    char[] letters = s.toCharArray();
    for (int i = 0; i < s.length(); ++i) {
      if (letters[i] == 'X') {
        for (int j = 0; j < 3 && i + j < s.length(); ++j) {
          letters[i + j] = 'O';
        }

        ++result;
      }
    }

    return result;
  }
}
