import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
	public int[] findRightInterval(int[][] intervals) {
		Element[] elements = IntStream.range(0, intervals.length).mapToObj(i -> new Element(i, intervals[i])).sorted()
				.toArray(Element[]::new);

		int[] result = new int[intervals.length];
		for (int i = 0; i < result.length; ++i) {
			int index = Arrays.binarySearch(elements, new Element(-1, new int[] { intervals[i][1], -1 }));
			if (index < 0) {
				index = -1 - index;
			}

			result[i] = (index == elements.length) ? -1 : elements[index].index;
		}

		return result;
	}
}

class Element implements Comparable<Element> {
	int index;
	int[] interval;

	Element(int index, int[] interval) {
		this.index = index;
		this.interval = interval;
	}

	@Override
	public int compareTo(Element other) {
		return Integer.compare(interval[0], other.interval[0]);
	}
}