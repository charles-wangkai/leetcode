import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] findDiagonalOrder(List<List<Integer>> nums) {
    return IntStream.range(0, nums.size())
        .boxed()
        .flatMap(r -> IntStream.range(0, nums.get(r).size()).mapToObj(c -> new Point(r, c)))
        .sorted(Comparator.<Point, Integer>comparing(p -> p.r() + p.c()).thenComparing(Point::c))
        .mapToInt(p -> nums.get(p.r()).get(p.c()))
        .toArray();
  }
}

record Point(int r, int c) {}
