public class Solution {
	public String decodeAtIndex(String S, int K) {
		long[] lengths = buildLengths(S);

		return String.valueOf(search(S, lengths, S.length() - 1, K));
	}

	char search(String S, long[] lengths, int index, long sequence) {
		char ch = S.charAt(index);
		if (Character.isLetter(ch)) {
			if (sequence == lengths[index]) {
				return ch;
			}

			return search(S, lengths, index - 1, sequence);
		} else {
			long nextSequence = sequence % lengths[index - 1];
			if (nextSequence == 0) {
				nextSequence = lengths[index - 1];
			}

			return search(S, lengths, index - 1, nextSequence);
		}
	}

	long[] buildLengths(String S) {
		long[] lengths = new long[S.length()];
		long length = 0;
		for (int i = 0; i < lengths.length; i++) {
			if (Character.isLetter(S.charAt(i))) {
				length++;
			} else {
				length *= S.charAt(i) - '0';
			}

			lengths[i] = length;
		}
		return lengths;
	}
}
