public class SortTransformedArray {
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		if (nums.length == 0) {
			return new int[0];
		}

		int[] result;
		if (a >= 0) {
			result = transform(nums, a, b, c);
		} else {
			result = transform(nums, -a, -b, -c);
		}

		if (a < 0) {
			result = negate(result);
		}
		return result;
	}

	int[] transform(int[] nums, int a, int b, int c) {
		int[] mapped = new int[nums.length];
		for (int i = 0; i < mapped.length; i++) {
			mapped[i] = f(nums[i], a, b, c);
		}

		int minIndex = 0;
		for (int i = 1; i < mapped.length; i++) {
			if (mapped[i] < mapped[minIndex]) {
				minIndex = i;
			}
		}

		int leftIndex = minIndex - 1;
		int rightIndex = minIndex;
		int[] transformed = new int[nums.length];
		for (int i = 0; i < transformed.length; i++) {
			if (rightIndex == nums.length || (leftIndex >= 0 && mapped[leftIndex] < mapped[rightIndex])) {
				transformed[i] = mapped[leftIndex];
				leftIndex--;
			} else {
				transformed[i] = mapped[rightIndex];
				rightIndex++;
			}
		}
		return transformed;
	}

	int f(int x, int a, int b, int c) {
		return a * x * x + b * x + c;
	}

	int[] negate(int[] a) {
		int[] result = new int[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = -a[a.length - 1 - i];
		}
		return result;
	}
}
