import java.util.HashMap;
import java.util.Map;

class BusBooking {
  Map<String, Integer> seatToTime = new HashMap<>();
  int totalTime = 0;

  public BusBooking(int n) {}

  public void toggle(String seat) {
    if (seatToTime.containsKey(seat)) {
      totalTime -= seatToTime.remove(seat);
    } else {
      int time =
          ((seat.charAt(0) == 'A' && seatToTime.containsKey('B' + seat.substring(1)))
                  || (seat.charAt(0) == 'D' && seatToTime.containsKey('C' + seat.substring(1))))
              ? 3
              : 1;

      seatToTime.put(seat, time);
      totalTime += time;
    }
  }

  public int getTotalTime() {
    return totalTime;
  }

  public int getMinTime() {
    return seatToTime.size();
  }
}

// Your BusBooking object will be instantiated and called as such:
// BusBooking obj = new BusBooking(n);
// obj.toggle(seat);
// int param_2 = obj.getTotalTime();
// int param_3 = obj.getMinTime();
