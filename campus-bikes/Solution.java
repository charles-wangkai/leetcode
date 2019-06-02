import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public int[] assignBikes(int[][] workers, int[][] bikes) {
		int N = workers.length;
		int M = bikes.length;

		int[] result = new int[N];
		Arrays.fill(result, -1);

		boolean[] bikeUsed = new boolean[M];

		List<Element> sortedElements = IntStream.range(0, N).boxed()
				.flatMap(workerIndex -> IntStream.range(0, M)
						.mapToObj(bikeIndex -> new Element(workerIndex, bikeIndex,
								computeDistance(workers, bikes, workerIndex, bikeIndex))))
				.sorted((element1, element2) -> {
					if (element1.distance != element2.distance) {
						return Integer.compare(element1.distance, element2.distance);
					} else if (element1.workerIndex != element2.workerIndex) {
						return Integer.compare(element1.workerIndex, element2.workerIndex);
					} else {
						return Integer.compare(element1.bikeIndex, element2.bikeIndex);
					}
				}).collect(Collectors.toList());

		for (Element element : sortedElements) {
			if (result[element.workerIndex] == -1 && !bikeUsed[element.bikeIndex]) {
				result[element.workerIndex] = element.bikeIndex;
				bikeUsed[element.bikeIndex] = true;
			}
		}

		return result;
	}

	int computeDistance(int[][] workers, int[][] bikes, int workerIndex, int bikeIndex) {
		return Math.abs(workers[workerIndex][0] - bikes[bikeIndex][0])
				+ Math.abs(workers[workerIndex][1] - bikes[bikeIndex][1]);
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