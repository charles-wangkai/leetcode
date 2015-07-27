import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses {
	@SuppressWarnings("unchecked")
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> numbers = new ArrayList<Integer>();
		List<Character> operators = new ArrayList<Character>();
		parse(input, numbers, operators);

		int numberNum = numbers.size();
		List<Integer>[][] results = new List[numberNum][];
		for (int i = 0; i < results.length; i++) {
			results[i] = new List[numberNum - i + 1];
			for (int j = 0; j < results[i].length; j++) {
				results[i][j] = new ArrayList<Integer>();
			}
		}

		for (int i = 0; i < results.length; i++) {
			results[i][1].add(numbers.get(i));
		}
		for (int j = 2; j <= numberNum; j++) {
			for (int i = 0; i <= numberNum - j; i++) {
				for (int k = 1; k < j; k++) {
					results[i][j].addAll(merge(results[i][k], results[i + k][j
							- k], operators.get(i + k - 1)));
				}
			}
		}

		return results[0][numberNum];
	}

	List<Integer> merge(List<Integer> results1, List<Integer> results2,
			char operator) {
		List<Integer> merged = new ArrayList<Integer>();
		for (int result1 : results1) {
			for (int result2 : results2) {
				merged.add(operate(result1, result2, operator));
			}
		}
		return merged;
	}

	int operate(int x, int y, char operator) {
		if (operator == '+') {
			return x + y;
		} else if (operator == '-') {
			return x - y;
		} else { // operator == '*'
			return x * y;
		}
	}

	void parse(String input, List<Integer> numbers, List<Character> operators) {
		int number = 0;
		for (int i = 0; i <= input.length(); i++) {
			if (i < input.length() && Character.isDigit(input.charAt(i))) {
				number = number * 10 + (input.charAt(i) - '0');
			} else {
				numbers.add(number);
				number = 0;

				if (i < input.length()) {
					operators.add(input.charAt(i));
				}
			}
		}
	}
}
