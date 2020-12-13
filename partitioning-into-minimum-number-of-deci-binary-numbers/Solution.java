class Solution {
  public int minPartitions(String n) {
    return n.chars().map(ch -> ch - '0').max().getAsInt();
  }
}
