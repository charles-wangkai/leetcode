import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {
  Queue<Integer> times = new LinkedList<>();

  public int ping(int t) {
    times.offer(t);

    while (t - times.peek() > 3000) {
      times.poll();
    }

    return times.size();
  }
}

// Your RecentCounter object will be instantiated and called as such:
// RecentCounter obj = new RecentCounter();
// int param_1 = obj.ping(t);
