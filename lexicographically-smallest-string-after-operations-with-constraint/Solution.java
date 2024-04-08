class Solution {
  public String getSmallestString(String s, int k) {
    StringBuilder result = new StringBuilder();
    for (char c : s.toCharArray()) {
      for (char a = 'a'; ; ++a) {
        int distance = Math.min(Math.abs(a - c), 26 - Math.abs(a - c));
        if (distance <= k) {
          result.append(a);
          k -= distance;

          break;
        }
      }
    }

    return result.toString();
  }
}