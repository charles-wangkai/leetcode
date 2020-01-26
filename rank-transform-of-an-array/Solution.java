import java.util.stream.IntStream;

public class Solution {
	public int[] arrayRankTransform(int[] arr) {
		Element[] elements = IntStream.range(0, arr.length).mapToObj(i -> new Element(i, arr[i]))
				.sorted((e1, e2) -> Integer.compare(e1.value, e2.value)).toArray(Element[]::new);

		int[] result = new int[arr.length];
		for (int i = 0; i < elements.length; ++i) {
			if (i == 0) {
				result[elements[i].index] = 1;
			} else {
				result[elements[i].index] = result[elements[i - 1].index]
						+ ((elements[i].value != elements[i - 1].value) ? 1 : 0);
			}
		}

		return result;
	}
}

class Element {
	int index;
	int value;

	Element(int index, int value) {
		this.index = index;
		this.value = value;
	}
}