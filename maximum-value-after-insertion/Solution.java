class Solution {
  public String maxValue(String n, int x) {
    if (n.charAt(0) == '-') {
      return String.format("-%s", findMin(n.substring(1), x));
    } else {
      return findMax(n, x);
    }
  }

  String findMax(String n, int x) {
    int index = 0;
    while (index != n.length() && x <= n.charAt(index) - '0') {
      ++index;
    }

    return String.format("%s%d%s", n.substring(0, index), x, n.substring(index));
  }

  String findMin(String n, int x) {
    int index = 0;
    while (index != n.length() && x >= n.charAt(index) - '0') {
      ++index;
    }

    return String.format("%s%d%s", n.substring(0, index), x, n.substring(index));
  }
}
