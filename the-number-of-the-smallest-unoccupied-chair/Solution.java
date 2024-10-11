import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public int smallestChair(int[][] times, int targetFriend) {
    Event[] events =
        IntStream.range(0, times.length)
            .boxed()
            .flatMap(
                i -> Stream.of(new Event(times[i][0], true, i), new Event(times[i][1], false, i)))
            .sorted(Comparator.comparing(Event::time).thenComparing(Event::arriveOrLeave))
            .toArray(Event[]::new);

    PriorityQueue<Integer> unoccupied = new PriorityQueue<>();
    int minChairId = 0;

    int[] chairs = new int[times.length];
    for (int i = 0; ; ++i) {
      if (events[i].arriveOrLeave()) {
        int chair;
        if (unoccupied.isEmpty()) {
          chair = minChairId;
          ++minChairId;
        } else {
          chair = unoccupied.poll();
        }

        if (events[i].friend() == targetFriend) {
          return chair;
        }

        chairs[events[i].friend()] = chair;
      } else {
        unoccupied.offer(chairs[events[i].friend()]);
      }
    }
  }
}

record Event(int time, boolean arriveOrLeave, int friend) {}
