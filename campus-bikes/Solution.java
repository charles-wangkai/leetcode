import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] assignBikes(int[][] workers, int[][] bikes) {
    int[] result = new int[workers.length];
    Arrays.fill(result, -1);

    boolean[] bikeUsed = new boolean[bikes.length];

    Element[] sortedElements =
        IntStream.range(0, workers.length)
            .boxed()
            .flatMap(
                workerIndex ->
                    IntStream.range(0, bikes.length)
                        .mapToObj(
                            bikeIndex ->
                                new Element(
                                    workerIndex,
                                    bikeIndex,
                                    computeDistance(workers[workerIndex], bikes[bikeIndex]))))
            .sorted(
                Comparator.comparing(Element::distance)
                    .thenComparing(Element::workerIndex)
                    .thenComparing(Element::bikeIndex))
            .toArray(Element[]::new);

    for (Element element : sortedElements) {
      if (result[element.workerIndex()] == -1 && !bikeUsed[element.bikeIndex()]) {
        result[element.workerIndex()] = element.bikeIndex();
        bikeUsed[element.bikeIndex()] = true;
      }
    }

    return result;
  }

  int computeDistance(int[] worker, int[] bike) {
    return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
  }
}

record Element(int workerIndex, int bikeIndex, int distance) {}
