public class CreateMaximumNumber {
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int[] result = null;
		for (int length1 = Math.max(0, k - nums2.length); length1 <= Math.min(nums1.length, k); length1++) {
			int[] merged = merge(findMaxDigits(nums1, length1), findMaxDigits(nums2, k - length1));
			if (isGreater(merged, result)) {
				result = merged;
			}
		}
		return result;
	}

	boolean isGreater(int[] number1, int[] number2) {
		if (number2 == null) {
			return true;
		}

		int index = 0;
		while (index < number1.length && index < number2.length && number1[index] == number2[index]) {
			index++;
		}
		return index < number1.length && number1[index] > number2[index];
	}

	int[] findMaxDigits(int[] nums, int length) {
		int[] maxDigits = new int[length];
		int size = 0;
		for (int i = 0; i < nums.length; i++) {
			while (size > 0 && nums[i] > maxDigits[size - 1] && (size - 1) + (nums.length - i) >= length) {
				size--;
			}

			if (size < length) {
				maxDigits[size] = nums[i];
				size++;
			}
		}
		return maxDigits;
	}

	int[] merge(int[] digits1, int[] digits2) {
		int[] merged = new int[digits1.length + digits2.length];
		int index1 = 0;
		int index2 = 0;
		for (int i = 0; i < merged.length; i++) {
			if (shouldChooseFromFirst(digits1, index1, digits2, index2)) {
				merged[i] = digits1[index1];
				index1++;
			} else {
				merged[i] = digits2[index2];
				index2++;
			}
		}
		return merged;
	}

	boolean shouldChooseFromFirst(int[] digits1, int index1, int[] digits2, int index2) {
		while (index1 < digits1.length && index2 < digits2.length && digits1[index1] == digits2[index2]) {
			index1++;
			index2++;
		}
		return index2 == digits2.length || (index1 < digits1.length && digits1[index1] > digits2[index2]);
	}
}
