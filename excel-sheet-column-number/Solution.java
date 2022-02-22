class Solution {
  public int titleToNumber(String columnTitle) {
    return columnTitle.chars().reduce(0, (result, ch) -> result * 26 + (ch - 'A' + 1));
  }
}
