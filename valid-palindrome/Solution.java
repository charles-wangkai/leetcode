import java.util.stream.Collectors;

class Solution {
	public boolean isPalindrome(String s) {
		String str = s.chars().filter(Character::isLetterOrDigit).mapToObj(ch -> String.valueOf((char) ch))
				.collect(Collectors.joining());

		return new StringBuilder(str).reverse().toString().equalsIgnoreCase(str);
	}
}
