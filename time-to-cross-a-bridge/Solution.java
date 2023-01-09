import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int findCrossingTime(int n, int k, int[][] time) {
    Comparator<Event> efficiencyComparator =
        Comparator.comparing((Event e) -> time[e.workerIndex()][0] + time[e.workerIndex()][2])
            .reversed()
            .thenComparing(Comparator.comparing(Event::workerIndex).reversed());

    PriorityQueue<Event> leftFutures = new PriorityQueue<>(Comparator.comparing(Event::time));
    PriorityQueue<Event> leftWaits = new PriorityQueue<>(efficiencyComparator);
    PriorityQueue<Event> rightFutures = new PriorityQueue<>(Comparator.comparing(Event::time));
    PriorityQueue<Event> rightWaits = new PriorityQueue<>(efficiencyComparator);

    for (int i = 0; i < k; ++i) {
      leftFutures.offer(new Event(0, i));
    }

    int result = -1;
    int currentTime = 0;
    while (!leftFutures.isEmpty()
        || !leftWaits.isEmpty()
        || !rightFutures.isEmpty()
        || !rightWaits.isEmpty()) {
      while (!leftFutures.isEmpty() && leftFutures.peek().time() <= currentTime) {
        leftWaits.offer(leftFutures.poll());
      }
      while (!rightFutures.isEmpty() && rightFutures.peek().time() <= currentTime) {
        rightWaits.offer(rightFutures.poll());
      }

      if (leftWaits.isEmpty() && rightWaits.isEmpty()) {
        currentTime =
            Math.min(
                leftFutures.isEmpty() ? Integer.MAX_VALUE : leftFutures.peek().time(),
                rightFutures.isEmpty() ? Integer.MAX_VALUE : rightFutures.peek().time());
      } else if (!rightWaits.isEmpty()) {
        Event head = rightWaits.poll();

        currentTime += time[head.workerIndex()][2];
        leftFutures.offer(new Event(currentTime + time[head.workerIndex()][3], head.workerIndex()));

        result = currentTime;
      } else {
        Event head = leftWaits.poll();

        if (n != 0) {
          currentTime += time[head.workerIndex()][0];
          rightFutures.offer(
              new Event(currentTime + time[head.workerIndex()][1], head.workerIndex()));

          --n;
        }
      }
    }

    return result;
  }
}

record Event(int time, int workerIndex) {}
