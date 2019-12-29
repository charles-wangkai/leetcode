import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {
	static int[] letterToDigit = new int['Z' + 1];

	public boolean isSolvable(String[] words, String result) {
		int[] letters = Stream.concat(Arrays.stream(words), Stream.of(result)).flatMapToInt(String::chars).distinct()
				.toArray();
		Set<Integer> leadingLetters = Stream.concat(Arrays.stream(words), Stream.of(result)).map(s -> (int) s.charAt(0))
				.collect(Collectors.toSet());

		return search(words, result, letters, leadingLetters, IntStream.range(0, 10).toArray(), 0);
	}

	boolean search(String[] words, String result, int[] letters, Set<Integer> leadingLetters, int[] digits, int index) {
		if (index == letters.length) {
			for (int i = 0; i < letters.length; ++i) {
				letterToDigit[letters[i]] = digits[i];
			}

			return check(words, result);
		}

		for (int i = index; i < digits.length; ++i) {
			if (leadingLetters.contains(letters[index]) && digits[i] == 0) {
				continue;
			}

			swap(digits, i, index);

			if (search(words, result, letters, leadingLetters, digits, index + 1)) {
				return true;
			}

			swap(digits, i, index);
		}

		return false;
	}

	void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

	boolean check(String[] words, String result) {
		int sum = 0;
		for (String word : words) {
			sum += convertToNumber(word);
		}

		return sum == convertToNumber(result);
	}

	int convertToNumber(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); ++i) {
			result = result * 10 + letterToDigit[s.charAt(i)];
		}

		return result;
	}
}
