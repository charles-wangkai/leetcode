import java.util.PriorityQueue;

class Solution {
  public int[] timeTaken(int[] arrival, int[] state) {
    int[] result = new int[arrival.length];
    int lastTime = Integer.MIN_VALUE;
    boolean lastEnterOrExit = false;
    int currentTime = -1;
    int index = 0;
    PriorityQueue<Integer> enterWaits = new PriorityQueue<>();
    PriorityQueue<Integer> exitWaits = new PriorityQueue<>();
    while (!enterWaits.isEmpty() || !exitWaits.isEmpty() || index != arrival.length) {
      while (index != arrival.length && arrival[index] <= currentTime) {
        if (state[index] == 0) {
          enterWaits.offer(index);
        } else {
          exitWaits.offer(index);
        }
        ++index;
      }

      if (enterWaits.isEmpty() && exitWaits.isEmpty()) {
        currentTime = arrival[index];
      } else if (!enterWaits.isEmpty()
          && (exitWaits.isEmpty() || (currentTime == lastTime + 1 && lastEnterOrExit))) {
        result[enterWaits.poll()] = currentTime;

        lastTime = currentTime;
        lastEnterOrExit = true;

        ++currentTime;
      } else {
        result[exitWaits.poll()] = currentTime;

        lastTime = currentTime;
        lastEnterOrExit = false;

        ++currentTime;
      }
    }

    return result;
  }
}
