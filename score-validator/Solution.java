class Solution {
  public int[] scoreValidator(String[] events) {
    int score = 0;
    int counter = 0;
    for (String event : events) {
      if (event.equals("W")) {
        ++counter;
        if (counter == 10) {
          break;
        }
      } else if (event.equals("WD") || event.equals("NB")) {
        ++score;
      } else {
        score += Integer.parseInt(event);
      }
    }

    return new int[] {score, counter};
  }
}