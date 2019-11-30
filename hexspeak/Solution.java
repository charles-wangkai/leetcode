public class Solution {
	static final String ALPHABET = "ABCDEFIO";

	public String toHexspeak(String num) {
		String result = Long.toHexString(Long.parseLong(num)).toUpperCase().replace('0', 'O').replace('1', 'I');

		return result.chars().allMatch(ch -> ALPHABET.indexOf(ch) >= 0) ? result : "ERROR";
	}
}
