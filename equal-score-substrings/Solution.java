class Solution {
  public boolean scoreBalance(String s) {
    int leftScore = 0;
    int rightScore = s.chars().map(c -> getScore((char) c)).sum();
    for (char c : s.toCharArray()) {
      int score = getScore(c);
      leftScore += score;
      rightScore -= score;
      if (leftScore == rightScore) {
        return true;
      }
    }

    return false;
  }

  int getScore(char c) {
    return c - 'a' + 1;
  }
}