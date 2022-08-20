class Solution {
  public int secondsToRemoveOccurrences(String s) {
    int result = 0;
    while (true) {
      String next = s.replace("01", "10");
      if (next.equals(s)) {
        break;
      }

      s = next;
      ++result;
    }

    return result;
  }
}