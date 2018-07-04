import java.util.HashSet;
import java.util.Set;

public class Solution {
	public int longestConsecutive(int[] num) {
		Set<Integer> numbers = new HashSet<Integer>();
		for (int number : num) {
			numbers.add(number);
		}
		int maxLength = 0;
		while (!numbers.isEmpty()) {
			int middle = numbers.iterator().next();
			int length = 0;
			for (int i = middle; numbers.contains(i); i++) {
				numbers.remove(i);
				length++;
			}
			for (int i = middle - 1; numbers.contains(i); i--) {
				numbers.remove(i);
				length++;
			}
			maxLength = Math.max(maxLength, length);
		}
		return maxLength;
	}
}
