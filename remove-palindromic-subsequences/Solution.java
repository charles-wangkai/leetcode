public class Solution {
	public int removePalindromeSub(String s) {
		if (s.isEmpty()) {
			return 0;
		} else if (s.equals(new StringBuilder(s).reverse().toString())) {
			return 1;
		} else {
			return 2;
		}
	}
}
