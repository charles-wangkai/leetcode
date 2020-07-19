class Solution {
	public String addBinary(String a, String b) {
		StringBuilder result = new StringBuilder();
		int carry = 0;
		for (int indexA = a.length() - 1, indexB = b.length() - 1; indexA >= 0 || indexB >= 0; --indexA, --indexB) {
			int sum = (indexA >= 0 ? (a.charAt(indexA) - '0') : 0) + (indexB >= 0 ? (b.charAt(indexB) - '0') : 0)
					+ carry;
			result.append(sum % 2);
			carry = sum / 2;
		}
		if (carry != 0) {
			result.append(carry);
		}

		return result.reverse().toString();
	}
}
