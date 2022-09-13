class Solution {
  public boolean validUtf8(int[] data) {
    int followRest = 0;
    for (int part : data) {
      if (followRest == 0) {
        if ((part & (1 << 7)) == 0) {
          followRest = 0;
        } else if ((part & (1 << 6)) == 0) {
          return false;
        } else if ((part & (1 << 5)) == 0) {
          followRest = 1;
        } else if ((part & (1 << 4)) == 0) {
          followRest = 2;
        } else if ((part & (1 << 3)) == 0) {
          followRest = 3;
        } else {
          return false;
        }
      } else {
        if ((part & (1 << 7)) == 0 || (part & (1 << 6)) != 0) {
          return false;
        }

        --followRest;
      }
    }

    return followRest == 0;
  }
}
