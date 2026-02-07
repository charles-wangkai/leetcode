class Solution {
  public int minimumDeletions(String s) {
    int deleteCount = (int) s.chars().filter(c -> c == 'a').count();
    int result = deleteCount;
    for (char c : s.toCharArray()) {
      if (c == 'a') {
        --deleteCount;
      } else {
        ++deleteCount;
      }

      result = Math.min(result, deleteCount);
    }

    return result;
  }
}
