class Solution {
  public int maximum69Number(int num) {
    String s = String.valueOf(num);
    int index = s.indexOf('6');

    return (index >= 0)
        ? Integer.parseInt(String.format("%s9%s", s.substring(0, index), s.substring(index + 1)))
        : num;
  }
}
