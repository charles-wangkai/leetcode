class Solution {
  public int bestClosingTime(String customers) {
    int yCount = (int) customers.chars().filter(c -> c == 'Y').count();
    int nCount = 0;
    int minPenalty = yCount;
    int result = 0;
    for (int i = 0; i < customers.length(); ++i) {
      if (customers.charAt(i) == 'Y') {
        --yCount;
      } else {
        ++nCount;
      }

      int penalty = yCount + nCount;
      if (penalty < minPenalty) {
        minPenalty = penalty;
        result = i + 1;
      }
    }

    return result;
  }
}
