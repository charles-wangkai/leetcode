class Solution {
  public int minPartitions(String n) {
    return n.chars().map(c -> c - '0').max().getAsInt();
  }
}
