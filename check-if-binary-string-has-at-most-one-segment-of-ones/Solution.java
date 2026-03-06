class Solution {
  public boolean checkOnesSegment(String s) {
    int index0 = s.indexOf('0');

    return index0 == -1 || s.indexOf('1', index0) == -1;
  }
}
