class Solution {
  public int distMoney(int money, int children) {
    for (int i = children; i >= 0; --i) {
      if (check(money - i * 8, children - i)) {
        return i;
      }
    }

    return -1;
  }

  boolean check(int restMoney, int restChildren) {
    if (restMoney < restChildren) {
      return false;
    }

    if (restChildren == 0) {
      return restMoney == 0;
    }
    if (restChildren == 1) {
      return restMoney != 4;
    }

    return true;
  }
}
