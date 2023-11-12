class Solution {
  public String kthLuckyNumber(int k) {
    StringBuilder sb = new StringBuilder();
    while (k != 0) {
      sb.append(((k - 1) % 2 == 0) ? '4' : '7');
      k = (k - 1) / 2;
    }

    return sb.reverse().toString();
  }
}
