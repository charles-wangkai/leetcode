import java.util.PriorityQueue;

class SeatManager {
  PriorityQueue<Integer> availables = new PriorityQueue<>();
  int next = 1;

  public SeatManager(int n) {}

  public int reserve() {
    if (availables.isEmpty()) {
      int result = next;
      ++next;

      return result;
    }

    return availables.poll();
  }

  public void unreserve(int seatNumber) {
    availables.offer(seatNumber);
  }
}

// Your SeatManager object will be instantiated and called as such:
// SeatManager obj = new SeatManager(n);
// int param_1 = obj.reserve();
// obj.unreserve(seatNumber);
