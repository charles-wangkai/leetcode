class Solution {
	public int titleToNumber(String s) {
		return s.chars().reduce(0, (result, ch) -> result * 26 + (ch - 'A' + 1));
	}
}
