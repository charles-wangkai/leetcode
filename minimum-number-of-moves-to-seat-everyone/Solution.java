import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minMovesToSeat(int[] seats, int[] students) {
    Arrays.sort(seats);
    Arrays.sort(students);

    return IntStream.range(0, seats.length).map(i -> Math.abs(seats[i] - students[i])).sum();
  }
}
