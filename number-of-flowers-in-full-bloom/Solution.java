import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
    int[] result = new int[persons.length];

    PriorityQueue<Integer> rightFlowerIndices =
        new PriorityQueue<>(Comparator.comparing(i -> flowers[i][0]));
    for (int i = 0; i < flowers.length; ++i) {
      rightFlowerIndices.offer(i);
    }
    PriorityQueue<Integer> middleFlowerIndices =
        new PriorityQueue<>(Comparator.comparing(i -> flowers[i][1]));
    int[] sortedIndices =
        IntStream.range(0, persons.length)
            .boxed()
            .sorted(Comparator.comparing(i -> persons[i]))
            .mapToInt(x -> x)
            .toArray();
    for (int index : sortedIndices) {
      while (!rightFlowerIndices.isEmpty()
          && flowers[rightFlowerIndices.peek()][0] <= persons[index]) {
        middleFlowerIndices.offer(rightFlowerIndices.poll());
      }
      while (!middleFlowerIndices.isEmpty()
          && flowers[middleFlowerIndices.peek()][1] < persons[index]) {
        middleFlowerIndices.poll();
      }

      result[index] = middleFlowerIndices.size();
    }

    return result;
  }
}