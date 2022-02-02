import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<Integer> findAnagrams(String s, String p) {
    if (s.length() < p.length()) {
      return List.of();
    }

    List<Integer> result = new ArrayList<>();
    int[] target = buildCounts(p);
    int[] counts = buildCounts(s.substring(0, p.length() - 1));
    for (int i = p.length() - 1; i < s.length(); ++i) {
      ++counts[s.charAt(i) - 'a'];

      if (Arrays.equals(counts, target)) {
        result.add(i - p.length() + 1);
      }

      --counts[s.charAt(i - p.length() + 1) - 'a'];
    }

    return result;
  }

  int[] buildCounts(String str) {
    int[] counts = new int[26];
    for (char ch : str.toCharArray()) {
      ++counts[ch - 'a'];
    }

    return counts;
  }
}
