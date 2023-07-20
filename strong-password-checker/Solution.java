// https://leetcode.com/problems/strong-password-checker/solutions/91008/simple-python-solution/

class Solution {
  public int strongPasswordChecker(String password) {
    int missingType =
        (password.chars().anyMatch(Character::isLowerCase) ? 0 : 1)
            + (password.chars().anyMatch(Character::isUpperCase) ? 0 : 1)
            + (password.chars().anyMatch(Character::isDigit) ? 0 : 1);

    int change = 0;
    int one = 0;
    int two = 0;
    int p = 2;
    while (p < password.length()) {
      if (password.charAt(p) == password.charAt(p - 1)
          && password.charAt(p - 1) == password.charAt(p - 2)) {
        int length = 2;
        while (p < password.length() && password.charAt(p) == password.charAt(p - 1)) {
          ++length;
          ++p;
        }

        change += length / 3;
        if (length % 3 == 0) {
          ++one;
        } else if (length % 3 == 1) {
          ++two;
        }
      } else {
        ++p;
      }
    }

    if (password.length() < 6) {
      return Math.max(missingType, 6 - password.length());
    }
    if (password.length() <= 20) {
      return Math.max(missingType, change);
    }

    int delete = password.length() - 20;

    change -= Math.min(delete, one);
    change -= Math.min(Math.max(0, delete - one), two * 2) / 2;
    change -= Math.max(0, delete - one - two * 2) / 3;

    return delete + Math.max(missingType, change);
  }
}
