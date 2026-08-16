import java.util.stream.IntStream;

class Solution {
  public int elevatorRequests(int n, int[] requests) {
    return IntStream.range(0, requests.length)
        .map(i -> Math.abs(requests[i] - ((i == 0) ? 0 : requests[i - 1])))
        .sum();
  }
}