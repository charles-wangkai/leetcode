class Solution {
  public boolean isNumber(String s) {
    int eIndex = s.toLowerCase().indexOf('e');
    if (eIndex == -1) {
      return isDecimalOrInteger(s);
    } else {
      return isDecimalOrInteger(s.substring(0, eIndex)) && isInteger(s.substring(eIndex + 1));
    }
  }

  boolean isDecimalOrInteger(String s) {
    return isDecimal(s) || isInteger(s);
  }

  boolean isDecimal(String s) {
    s = removeSign(s);
    int pointIndex = s.indexOf('.');

    return pointIndex != -1
        && s.length() != 1
        && (pointIndex == 0 || isIntegerWithoutSign(s.substring(0, pointIndex)))
        && (pointIndex == s.length() - 1 || isIntegerWithoutSign(s.substring(pointIndex + 1)));
  }

  boolean isInteger(String s) {
    return isIntegerWithoutSign(removeSign(s));
  }

  String removeSign(String s) {
    if (s.startsWith("+") || s.startsWith("-")) {
      s = s.substring(1);
    }

    return s;
  }

  boolean isIntegerWithoutSign(String s) {
    return !s.isEmpty() && s.chars().allMatch(Character::isDigit);
  }
}
