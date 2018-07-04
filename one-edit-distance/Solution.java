public class Solution {
	public boolean isOneEditDistance(String s, String t) {
		int index = 0;
		while (index < s.length() && index < t.length()
				&& s.charAt(index) == t.charAt(index)) {
			index++;
		}
		return (index < s.length() && index <= t.length() && s.substring(
				index + 1).equals(t.substring(index)))
				|| (index < s.length() && index < t.length() && s.substring(
						index + 1).equals(t.substring(index + 1)))
				|| (index <= s.length() && index < t.length() && s.substring(
						index).equals(t.substring(index + 1)));
	}
}
