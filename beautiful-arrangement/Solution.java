public class Solution {
	int result;

	public int countArrangement(int N) {
		int[] arrangement = new int[N];
		for (int i = 0; i < arrangement.length; i++) {
			arrangement[i] = i + 1;
		}

		result = 0;
		search(arrangement, 0);
		return result;
	}

	void search(int[] arrangement, int index) {
		if (index == arrangement.length) {
			result++;
			return;
		}

		for (int i = index; i < arrangement.length; i++) {
			if (arrangement[i] % (index + 1) == 0 || (index + 1) % arrangement[i] == 0) {
				swap(arrangement, index, i);
				search(arrangement, index + 1);
				swap(arrangement, index, i);
			}
		}
	}

	void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
}
