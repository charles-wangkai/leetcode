import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	static final char[] LETTERS = { 'A', 'C', 'G', 'T' };
	static final int LENGTH = 10;

	public List<String> findRepeatedDnaSequences(String s) {
		Set<Integer> singles = new HashSet<Integer>();
		Set<Integer> repeats = new HashSet<Integer>();
		for (int i = 0; i + LENGTH <= s.length(); i++) {
			int code = encode(s.substring(i, i + LENGTH));
			if (singles.contains(code)) {
				singles.remove(code);
				repeats.add(code);
			} else if (!repeats.contains(code)) {
				singles.add(code);
			}
		}

		List<String> result = new ArrayList<String>();
		for (int repeat : repeats) {
			result.add(decode(repeat));
		}
		return result;
	}

	int encode(String sequence) {
		int code = 0;
		for (int i = 0; i < sequence.length(); i++) {
			code = code * LETTERS.length
					+ Arrays.binarySearch(LETTERS, sequence.charAt(i));
		}
		return code;
	}

	String decode(int code) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < LENGTH; i++) {
			sb.append(LETTERS[code % LETTERS.length]);
			code /= LETTERS.length;
		}
		return sb.reverse().toString();
	}
}
