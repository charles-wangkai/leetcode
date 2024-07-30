class Solution {
  public int minimumDeletions(String s) {
    int aCount = (int) s.chars().filter(c -> c == 'a').count();
    int result = aCount;
    int bCount = 0;
    for (char c : s.toCharArray()) {
      if (c == 'a') {
        --aCount;
      } else {
        ++bCount;
      }

      result = Math.min(result, aCount + bCount);
    }

    return result;
  }
}
