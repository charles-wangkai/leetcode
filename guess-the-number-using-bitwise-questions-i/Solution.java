import java.util.stream.IntStream;

// Definition of commonSetBits API (defined in the parent class Problem).
class Problem {
  int commonSetBits(int num) {
    throw new UnsupportedOperationException();
  }
}

public class Solution extends Problem {
  public int findNumber() {
    return IntStream.range(0, 30).filter(i -> commonSetBits(1 << i) == 1).map(i -> 1 << i).sum();
  }
}