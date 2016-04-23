public class ReverseVowelsOfAString {
	public String reverseVowels(String s) {
		StringBuilder sb = new StringBuilder(s);

		for (int i = 0, j = s.length() - 1; i < j;) {
			if (!isVowel(sb.charAt(i))) {
				i++;
				continue;
			}

			if (!isVowel(sb.charAt(j))) {
				j--;
				continue;
			}

			swap(sb, i, j);
			i++;
			j--;
		}

		return sb.toString();
	}

	boolean isVowel(char letter) {
		return "aeiou".indexOf(Character.toLowerCase(letter)) >= 0;
	}

	void swap(StringBuilder sb, int index1, int index2) {
		char temp = sb.charAt(index1);
		sb.setCharAt(index1, sb.charAt(index2));
		sb.setCharAt(index2, temp);
	}
}
