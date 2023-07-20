import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficLight {
  Lock lock = new ReentrantLock();
  Condition canCrossCar = lock.newCondition();

  boolean greenOnRoad1 = true;
  int waitingCount1 = 0;
  int waitingCount2 = 0;

  public void carArrived(
      int carId, // ID of the car
      int roadId, // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
      int direction, // Direction of the car
      Runnable turnGreen, // Use turnGreen.run() to turn light to green on current road
      Runnable crossCar // Use crossCar.run() to make car cross the intersection
      ) {
    lock.lock();

    if (roadId == 1) {
      waitingCount1++;
    } else {
      waitingCount2++;
    }

    try {
      if (roadId == 1) {
        while (!(greenOnRoad1 || waitingCount2 == 0)) {
          canCrossCar.await();
        }

        if (!greenOnRoad1) {
          turnGreen.run();
          greenOnRoad1 = true;
        }
      } else {
        while (!(!greenOnRoad1 || waitingCount1 == 0)) {
          canCrossCar.await();
        }

        if (greenOnRoad1) {
          turnGreen.run();
          greenOnRoad1 = false;
        }
      }

      crossCar.run();

      canCrossCar.signal();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      if (roadId == 1) {
        waitingCount1--;
      } else {
        waitingCount2--;
      }

      lock.unlock();
    }
  }
}
