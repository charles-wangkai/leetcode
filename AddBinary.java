public class AddBinary {
	public String addBinary(String a, String b) {
		StringBuilder result = new StringBuilder();
		int carry = 0;
		for (int indexA = a.length() - 1, indexB = b.length() - 1; indexA >= 0
				|| indexB >= 0; indexA--, indexB--) {
			int sum = (indexA >= 0 ? convertToDigit(a.charAt(indexA)) : 0)
					+ (indexB >= 0 ? convertToDigit(b.charAt(indexB)) : 0)
					+ carry;
			result.append(sum % 2);
			carry = sum / 2;
		}
		if (carry > 0) {
			result.append(carry);
		}
		return result.reverse().toString();
	}

	int convertToDigit(char ch) {
		return ch - '0';
	}
}
