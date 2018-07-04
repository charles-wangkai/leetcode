import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public List<String> letterCombinations(String digits) {
		Map<Character, String> digit2letters = new HashMap<Character, String>();
		digit2letters.put('2', "abc");
		digit2letters.put('3', "def");
		digit2letters.put('4', "ghi");
		digit2letters.put('5', "jkl");
		digit2letters.put('6', "mno");
		digit2letters.put('7', "pqrs");
		digit2letters.put('8', "tuv");
		digit2letters.put('9', "wxyz");

		List<String> combinations = new ArrayList<String>();
		combinations.add("");
		for (int i = 0; i < digits.length(); i++) {
			List<String> nextCombinations = new ArrayList<String>();
			char digit = digits.charAt(i);
			String letters = digit2letters.get(digit);
			for (int j = 0; j < letters.length(); j++) {
				char letter = letters.charAt(j);
				for (String combination : combinations) {
					nextCombinations.add(combination + letter);
				}
			}
			combinations = nextCombinations;
		}
		return combinations;
	}
}
