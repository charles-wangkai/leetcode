import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
    Arrays.sort(buses);
    Arrays.sort(passengers);

    int result = -1;
    int lastTime = -1;
    int passengerIndex = 0;
    Queue<Integer> queue = new ArrayDeque<>();
    for (int bus : buses) {
      while (passengerIndex != passengers.length && passengers[passengerIndex] <= bus) {
        queue.offer(passengers[passengerIndex]);
        ++passengerIndex;
      }

      for (int i = 0; i < capacity; ++i) {
        if (queue.isEmpty()) {
          if (lastTime != bus) {
            result = bus;
          }

          break;
        } else {
          if (queue.peek() - 1 > lastTime) {
            result = queue.peek() - 1;
          }

          lastTime = queue.poll();
        }
      }
    }

    return result;
  }
}