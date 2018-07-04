import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int maxChunksToSorted(int[] arr) {
		int[] sorted = Arrays.copyOf(arr, arr.length);
		Arrays.sort(sorted);

		Map<Integer, Integer> num2index = new HashMap<Integer, Integer>();
		for (int i = 0; i < sorted.length; i++) {
			if (!num2index.containsKey(sorted[i])) {
				num2index.put(sorted[i], i);
			}
		}

		int chunkNum = 0;
		int maxNumber = -1;
		Map<Integer, Integer> num2count = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			int num = arr[i];
			maxNumber = Math.max(maxNumber, num2index.get(num) + num2count.getOrDefault(num, 0));

			if (maxNumber == i) {
				chunkNum++;
			}

			num2count.put(num, num2count.getOrDefault(num, 0) + 1);
		}
		return chunkNum;
	}
}
