import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	public List<Integer> transformArray(int[] arr) {
		while (true) {
			int[] next = new int[arr.length];
			for (int i = 0; i < next.length; i++) {
				next[i] = arr[i];

				if (i != 0 && i != next.length - 1) {
					if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
						next[i]++;
					} else if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
						next[i]--;
					}
				}
			}

			if (Arrays.equals(next, arr)) {
				break;
			}

			arr = next;
		}

		return Arrays.stream(arr).boxed().collect(Collectors.toList());
	}
}
