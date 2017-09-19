import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidParenthesisString {
	public boolean checkValidString(String s) {
		Set<Integer> numbers = new HashSet<Integer>();
		numbers.add(0);
		for (char ch : s.toCharArray()) {
			if (ch == '(') {
				numbers = shift(numbers, 1);
			} else if (ch == ')') {
				numbers = shift(numbers, -1);
			} else if (ch == '*') {
				numbers = union(numbers, union(shift(numbers, 1), shift(numbers, -1)));
			}
		}
		return numbers.contains(0);
	}

	Set<Integer> shift(Set<Integer> numbers, int delta) {
		return numbers.stream().map(number -> number + delta).filter(x -> x >= 0).collect(Collectors.toSet());
	}

	Set<Integer> union(Set<Integer> s1, Set<Integer> s2) {
		return Stream.concat(s1.stream(), s2.stream()).collect(Collectors.toSet());
	}
}
