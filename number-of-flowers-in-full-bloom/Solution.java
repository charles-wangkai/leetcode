import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public int[] fullBloomFlowers(int[][] flowers, int[] people) {
    Arrays.sort(flowers, Comparator.comparing(flower -> flower[0]));

    int[] result = new int[people.length];
    int flowerIndex = 0;
    PriorityQueue<Integer> flowerEndTimes = new PriorityQueue<>();
    int[] sortedPersonIndices =
        IntStream.range(0, people.length)
            .boxed()
            .sorted(Comparator.comparing(i -> people[i]))
            .mapToInt(Integer::intValue)
            .toArray();
    for (int personIndex : sortedPersonIndices) {
      while (flowerIndex != flowers.length && flowers[flowerIndex][0] <= people[personIndex]) {
        flowerEndTimes.offer(flowers[flowerIndex][1]);
        ++flowerIndex;
      }

      while (!flowerEndTimes.isEmpty() && flowerEndTimes.peek() < people[personIndex]) {
        flowerEndTimes.poll();
      }

      result[personIndex] = flowerEndTimes.size();
    }

    return result;
  }
}
