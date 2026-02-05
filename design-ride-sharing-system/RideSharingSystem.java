import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class RideSharingSystem {
  Queue<Integer> riderIds = new ArrayDeque<>();
  Queue<Integer> driverIds = new ArrayDeque<>();
  Set<Integer> seen = new HashSet<>();
  Set<Integer> cancelled = new HashSet<>();

  public void addRider(int riderId) {
    riderIds.offer(riderId);
    seen.add(riderId);
  }

  public void addDriver(int driverId) {
    driverIds.offer(driverId);
  }

  public int[] matchDriverWithRider() {
    while (!riderIds.isEmpty() && cancelled.contains(riderIds.peek())) {
      riderIds.poll();
    }

    if (riderIds.isEmpty() || driverIds.isEmpty()) {
      return new int[] {-1, -1};
    }

    int riderId = riderIds.poll();
    int driverId = driverIds.poll();

    return new int[] {driverId, riderId};
  }

  public void cancelRider(int riderId) {
    if (seen.contains(riderId)) {
      cancelled.add(riderId);
    }
  }
}

// Your RideSharingSystem object will be instantiated and called as such:
// RideSharingSystem obj = new RideSharingSystem();
// obj.addRider(riderId);
// obj.addDriver(driverId);
// int[] param_3 = obj.matchDriverWithRider();
// obj.cancelRider(riderId);
