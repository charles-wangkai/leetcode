class Solution {
  public boolean checkOnesSegment(String s) {
    int index0 = s.indexOf('0');
    if (index0 == -1) {
      return true;
    }

    return s.indexOf('1', index0) == -1;
  }
}
