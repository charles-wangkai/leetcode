class Solution {
  public int beautifulSubstrings(String s, int k) {
    int result = 0;
    for (int i = 0; i < s.length(); ++i) {
      int vowelCount = 0;
      int consonantCount = 0;
      for (int j = i; j < s.length(); ++j) {
        if ("aeiou".indexOf(s.charAt(j)) == -1) {
          ++consonantCount;
        } else {
          ++vowelCount;
        }

        if (vowelCount == consonantCount && vowelCount * consonantCount % k == 0) {
          ++result;
        }
      }
    }

    return result;
  }
}