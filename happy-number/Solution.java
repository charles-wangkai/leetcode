import java.util.HashSet;
import java.util.Set;

public class Solution {
	public boolean isHappy(int n) {
		Set<Integer> history = new HashSet<Integer>();
		while (!history.contains(n)) {
			history.add(n);
			n = computeNext(n);
		}
		return n == 1;
	}

	int computeNext(int number) {
		int next = 0;
		while (number != 0) {
			int digit = number % 10;
			next += digit * digit;
			number /= 10;
		}
		return next;
	}
}
