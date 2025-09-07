class Solution {
  public int minOperations(String s) {
    return s.chars().map(c -> Math.floorMod(-(c - 'a'), 26)).max().getAsInt();
  }
}