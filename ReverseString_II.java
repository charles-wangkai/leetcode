public class ReverseString_II {
	public String reverseStr(String s, int k) {
		for (int i = 0; i < s.length(); i += k * 2) {
			s = reverse(s, i, Math.min(i + k, s.length()));
		}
		return s;
	}

	String reverse(String s, int beginIndex, int endIndex) {
		return s.substring(0, beginIndex) + new StringBuilder(s.substring(beginIndex, endIndex)).reverse().toString()
				+ s.substring(endIndex);
	}
}
