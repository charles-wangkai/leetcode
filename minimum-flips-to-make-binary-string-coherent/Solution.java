class Solution {
  public int minFlips(String s) {
    if (s.length() < 3) {
      return 0;
    }

    int count0 = (int) s.chars().filter(c -> c == '0').count();
    int count1 = (int) s.chars().filter(c -> c == '1').count();

    if (count0 == 0 || count1 <= 1) {
      return 0;
    }

    return Math.min(count0, count1 - ((s.startsWith("1") && s.endsWith("1")) ? 2 : 1));
  }
}