import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	public List<List<Integer>> permuteUnique(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		search(result, num, 0);
		return result;
	}

	void search(List<List<Integer>> result, int[] num, int index) {
		if (index == num.length) {
			List<Integer> permutation = new ArrayList<Integer>();
			for (int oneNum : num) {
				permutation.add(oneNum);
			}
			result.add(permutation);
			return;
		}
		Set<Integer> used = new HashSet<Integer>();
		for (int i = index; i < num.length; i++) {
			if (used.contains(num[i])) {
				continue;
			}
			used.add(num[i]);
			swap(num, index, i);
			search(result, num, index + 1);
			swap(num, index, i);
		}
	}

	void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
}
