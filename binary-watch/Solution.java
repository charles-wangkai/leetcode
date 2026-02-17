import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> readBinaryWatch(int turnedOn) {
    List<String> result = new ArrayList<>();
    for (int hour = 0; hour <= 11; ++hour) {
      for (int minute = 0; minute <= 59; ++minute) {
        if (Integer.bitCount(hour) + Integer.bitCount(minute) == turnedOn) {
          result.add("%d:%02d".formatted(hour, minute));
        }
      }
    }

    return result;
  }
}
