import java.util.Arrays;

class Solution {
	public int compareVersion(String version1, String version2) {
		int[] numbers1 = convertToNumbers(version1);
		int[] numbers2 = convertToNumbers(version2);
		for (int i = 0; i < numbers1.length || i < numbers2.length; ++i) {
			int number1 = getNumber(numbers1, i);
			int number2 = getNumber(numbers2, i);
			if (number1 != number2) {
				return Integer.compare(number1, number2);
			}
		}

		return 0;
	}

	int[] convertToNumbers(String version) {
		return Arrays.stream(version.split("\\.")).mapToInt(Integer::parseInt).toArray();
	}

	int getNumber(int[] numbers, int index) {
		return (index < numbers.length) ? numbers[index] : 0;
	}
}
