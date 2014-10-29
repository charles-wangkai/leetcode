public class Implement_strStr {
	public String strStr(String haystack, String needle) {
		for (int i = 0; i + needle.length() <= haystack.length(); i++) {
			boolean match = true;
			for (int j = 0; j < needle.length(); j++) {
				if (haystack.charAt(i + j) != needle.charAt(j)) {
					match = false;
					break;
				}
			}
			if (match) {
				return haystack.substring(i);
			}
		}
		return null;
	}
}
