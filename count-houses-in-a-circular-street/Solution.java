// Definition for a street.
abstract class Street {
  Street(int[] doors) {}

  public abstract void openDoor();

  public abstract void closeDoor();

  public abstract boolean isDoorOpen();

  public abstract void moveRight();

  public abstract void moveLeft();
}

class Solution {
  public int houseCount(Street street, int k) {
    for (int i = 0; i < k; ++i) {
      street.closeDoor();
      street.moveRight();
    }

    int result = 0;
    street.openDoor();
    do {
      ++result;
      street.moveRight();
    } while (!street.isDoorOpen());

    return result;
  }
}
