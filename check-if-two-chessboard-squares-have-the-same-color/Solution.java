class Solution {
  public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
    return isBlack(coordinate1) == isBlack(coordinate2);
  }

  boolean isBlack(String coordinate) {
    return ((coordinate.charAt(0) - 'a') + (coordinate.charAt(1) - 'a')) % 2 == 0;
  }
}