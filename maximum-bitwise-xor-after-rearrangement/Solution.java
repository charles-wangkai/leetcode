class Solution {
  public String maximumXor(String s, String t) {
    int[] counts = new int[2];
    for (char c : t.toCharArray()) {
      ++counts[c - '0'];
    }

    char[] result = new char[s.length()];
    for (int i = 0; i < result.length; ++i) {
      int target = 1 - (s.charAt(i) - '0');
      if (counts[target] == 0) {
        result[i] = '0';
        --counts[1 - target];
      } else {
        result[i] = '1';
        --counts[target];
      }
    }

    return String.valueOf(result);
  }
}