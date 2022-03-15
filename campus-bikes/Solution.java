import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] assignBikes(int[][] workers, int[][] bikes) {
    int n = workers.length;
    int m = bikes.length;

    int[] result = new int[n];
    Arrays.fill(result, -1);

    boolean[] bikeUsed = new boolean[m];

    Element[] sortedElements =
        IntStream.range(0, n)
            .boxed()
            .flatMap(
                workerIndex ->
                    IntStream.range(0, m)
                        .mapToObj(
                            bikeIndex ->
                                new Element(
                                    workerIndex,
                                    bikeIndex,
                                    computeDistance(workers[workerIndex], bikes[bikeIndex]))))
            .sorted(
                (e1, e2) -> {
                  if (e1.distance != e2.distance) {
                    return Integer.compare(e1.distance, e2.distance);
                  } else if (e1.workerIndex != e2.workerIndex) {
                    return Integer.compare(e1.workerIndex, e2.workerIndex);
                  } else {
                    return Integer.compare(e1.bikeIndex, e2.bikeIndex);
                  }
                })
            .toArray(Element[]::new);

    for (Element element : sortedElements) {
      if (result[element.workerIndex] == -1 && !bikeUsed[element.bikeIndex]) {
        result[element.workerIndex] = element.bikeIndex;
        bikeUsed[element.bikeIndex] = true;
      }
    }

    return result;
  }

  int computeDistance(int[] worker, int[] bike) {
    return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
  }
}

class Element {
  int workerIndex;
  int bikeIndex;
  int distance;

  Element(int workerIndex, int bikeIndex, int distance) {
    this.workerIndex = workerIndex;
    this.bikeIndex = bikeIndex;
    this.distance = distance;
  }
}