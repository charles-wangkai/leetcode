// https://leetcode.com/problems/lexicographically-smallest-string-after-deleting-duplicate-characters/solutions/7503559/lexicographicallyoptimised-cjavapystack-ppz2r/

class Solution {
  public String lexSmallestAfterDeletion(String s) {
    int[] rests = new int[26];
    for (char c : s.toCharArray()) {
      ++rests[c - 'a'];
    }

    StringBuilder result = new StringBuilder();
    int[] counts = new int[26];
    for (char c : s.toCharArray()) {
      --rests[c - 'a'];

      while (!result.isEmpty()) {
        char last = result.charAt(result.length() - 1);
        if (last <= c || !(rests[last - 'a'] > 0 || counts[last - 'a'] > 1)) {
          break;
        }

        --counts[last - 'a'];
        result.deleteCharAt(result.length() - 1);
      }

      result.append(c);
      ++counts[c - 'a'];
    }
    while (!result.isEmpty() && counts[result.charAt(result.length() - 1) - 'a'] > 1) {
      --counts[result.charAt(result.length() - 1) - 'a'];
      result.deleteCharAt(result.length() - 1);
    }

    return result.toString();
  }
}
