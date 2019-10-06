import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
		List<Integer> result = new ArrayList<>();
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		while (index1 < arr1.length && index2 < arr2.length && index3 < arr3.length) {
			if (arr1[index1] == arr2[index2] && arr1[index1] == arr3[index3]) {
				result.add(arr1[index1]);

				index1++;
				index2++;
				index3++;
			} else {
				int max = Math.max(Math.max(arr1[index1], arr2[index2]), arr3[index3]);

				if (arr1[index1] != max) {
					index1++;
				}
				if (arr2[index2] != max) {
					index2++;
				}
				if (arr3[index3] != max) {
					index3++;
				}
			}
		}

		return result;
	}
}
