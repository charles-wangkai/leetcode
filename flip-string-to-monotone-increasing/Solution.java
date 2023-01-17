class Solution {
  public int minFlipsMonoIncr(String s) {
    int flipNum = (int) s.chars().filter(c -> c == '0').count();
    int result = flipNum;
    for (char c : s.toCharArray()) {
      if (c == '0') {
        --flipNum;
      } else {
        ++flipNum;
      }

      result = Math.min(result, flipNum);
    }

    return result;
  }
}
