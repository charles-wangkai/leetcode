import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public int[] advantageCount(int[] A, int[] B) {
		List<Integer> targetIndices = IntStream.range(0, B.length).boxed()
				.sorted((index1, index2) -> Integer.compare(B[index1], B[index2])).collect(Collectors.toList());

		Queue<Integer> remains = new LinkedList<>(Arrays.stream(A).sorted().boxed().collect(Collectors.toList()));
		Queue<Integer> fillers = new LinkedList<>();
		int[] result = new int[A.length];
		for (int targetIndex : targetIndices) {
			while (!remains.isEmpty() && remains.peek() <= B[targetIndex]) {
				fillers.offer(remains.poll());
			}

			result[targetIndex] = remains.isEmpty() ? fillers.poll() : remains.poll();
		}
		return result;
	}
}
