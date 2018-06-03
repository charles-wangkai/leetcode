import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SplitArrayIntoFibonacciSequence {
	public List<Integer> splitIntoFibonacci(String S) {
		for (int length0 = 1; length0 * 2 <= S.length(); length0++) {
			for (int length1 = 1; Math.max(length0, length1) * 2 <= S.length(); length1++) {
				List<Integer> sequence = findSequence(S, length0, length1);

				if (sequence != null) {
					return sequence;
				}
			}
		}

		return Collections.emptyList();
	}

	List<Integer> findSequence(String S, int length0, int length1) {
		try {
			List<Integer> sequence = new ArrayList<Integer>();

			int index = 0;

			sequence.add(parseInt(S.substring(index, index + length0)));
			index += length0;

			sequence.add(parseInt(S.substring(index, index + length1)));
			index += length1;

			while (index < S.length()) {
				long expected = sequence.get(sequence.size() - 2) + sequence.get(sequence.size() - 1);
				if (expected > Integer.MAX_VALUE) {
					return null;
				}

				int length = String.valueOf(expected).length();
				if (length > S.length() - index) {
					return null;
				}

				int next = parseInt(S.substring(index, index + length));
				index += length;

				if (next != expected) {
					return null;
				}

				sequence.add(next);
			}

			return sequence;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	int parseInt(String s) {
		if (s.equals("0")) {
			return 0;
		}

		if (s.startsWith("0")) {
			throw new IllegalArgumentException();
		}

		return Integer.parseInt(s);
	}
}
