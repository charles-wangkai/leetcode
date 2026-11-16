class Solution {
  public String digitSum(String s, int k) {
    if (s.length() <= k) {
      return s;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i += k) {
      sb.append(s.substring(i, Math.min(s.length(), i + k)).chars().map(c -> c - '0').sum());
    }

    return digitSum(sb.toString(), k);
  }
}