class Solution {
  public String shortestSuperstring(String s1, String s2) {
    String candidate1 = findSuperstring(s1, s2);
    String candidate2 = findSuperstring(s2, s1);

    return (candidate1.length() <= candidate2.length()) ? candidate1 : candidate2;
  }

  String findSuperstring(String a, String b) {
    if (a.contains(b)) {
      return a;
    }

    for (int i = 0; ; ++i) {
      if (b.startsWith(a.substring(i))) {
        return a.substring(0, i) + b;
      }
    }
  }
}