// https://leetcode.com/problems/count-houses-in-a-circular-street-ii/solutions/3698269/between-open-doors/

// Definition for a street.
abstract class Street {
  Street(int[] doors) {}

  public abstract void closeDoor();

  public abstract boolean isDoorOpen();

  public abstract void moveRight();
}

class Solution {
  public int houseCount(Street street, int k) {
    int firstOpen = -1;
    int lastOpen = 0;
    for (int i = 0; i - lastOpen <= k; ++i) {
      if (street.isDoorOpen()) {
        if (firstOpen == -1) {
          firstOpen = i;
        } else {
          street.closeDoor();
        }

        lastOpen = i;
      }

      street.moveRight();
    }

    return lastOpen - firstOpen;
  }
}
