public class NextPermutation {
	public void nextPermutation(int[] num) {
		int startIndex = num.length - 2;
		while (startIndex >= 0 && num[startIndex] >= num[startIndex + 1]) {
			startIndex--;
		}

		if (startIndex < 0) {
			reverse(num, 0, num.length - 1);
			return;
		}

		for (int i = num.length - 1;; i--) {
			if (num[i] > num[startIndex]) {
				swap(num, i, startIndex);
				reverse(num, startIndex + 1, num.length - 1);
				return;
			}
		}
	}

	void reverse(int[] a, int beginIndex, int endIndex) {
		for (int i = beginIndex, j = endIndex; i < j; i++, j--) {
			swap(a, i, j);
		}
	}

	void swap(int a[], int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
}
