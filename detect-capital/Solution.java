class Solution {
	public boolean detectCapitalUse(String word) {
		return word.chars().allMatch(Character::isUpperCase) || word.chars().allMatch(Character::isLowerCase)
				|| (Character.isUpperCase(word.charAt(0))
						&& word.substring(1).chars().allMatch(Character::isLowerCase));
	}
}
