class Solution {
  public int bestClosingTime(String customers) {
    int penalty = (int) customers.chars().filter(c -> c == 'Y').count();
    int minPenalty = penalty;
    int result = 0;
    for (int i = 0; i < customers.length(); ++i) {
      penalty += (customers.charAt(i) == 'Y') ? -1 : 1;
      if (penalty < minPenalty) {
        minPenalty = penalty;
        result = i + 1;
      }
    }

    return result;
  }
}
