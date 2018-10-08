import java.util.stream.IntStream;

public class Solution {
	public String reverseOnlyLetters(String S) {
		StringBuilder reversed = new StringBuilder(S);
		int[] letterIndices = IntStream.range(0, S.length()).filter(i -> Character.isLetter(S.charAt(i))).toArray();
		for (int i = 0, j = letterIndices.length - 1; i < j; i++, j--) {
			char temp = reversed.charAt(letterIndices[i]);
			reversed.setCharAt(letterIndices[i], reversed.charAt(letterIndices[j]));
			reversed.setCharAt(letterIndices[j], temp);
		}
		return reversed.toString();
	}
}
