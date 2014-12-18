import java.util.ArrayList;
import java.util.List;

public class CompareVersionNumbers {
	public int compareVersion(String version1, String version2) {
		List<Integer> numbers1 = convertToNumbers(version1);
		List<Integer> numbers2 = convertToNumbers(version2);
		for (int i = 0; i < numbers1.size() || i < numbers2.size(); i++) {
			int number1 = getNumber(numbers1, i);
			int number2 = getNumber(numbers2, i);
			int diff = number1 - number2;
			if (diff != 0) {
				return (int) Math.signum(diff);
			}
		}
		return 0;
	}

	List<Integer> convertToNumbers(String version) {
		List<Integer> numbers = new ArrayList<Integer>();
		for (String part : version.split("\\.")) {
			numbers.add(Integer.parseInt(part));
		}
		return numbers;
	}

	int getNumber(List<Integer> numbers, int index) {
		return (index < numbers.size()) ? numbers.get(index) : 0;
	}
}
