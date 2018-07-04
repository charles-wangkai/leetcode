public class Solution {
	public boolean checkRecord(String s) {
		return !(s.chars().filter(c -> c == 'A').count() > 1 || s.contains("LLL"));
	}
}
