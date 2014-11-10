public class MultiplyStrings {
	public String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) {
			return "0";
		}

		String result = "0";
		for (int i = 0; i < num1.length(); i++) {
			int digit = num1.charAt(i) - '0';
			result = add(result,
					appendZeros(multiply(num2, digit), num1.length() - 1 - i));
		}
		return result;
	}

	String multiply(String num, int digit) {
		StringBuilder result = new StringBuilder();
		int carry = 0;
		for (int i = num.length() - 1; i >= 0; i--) {
			int product = (num.charAt(i) - '0') * digit + carry;
			result.append(product % 10);
			carry = product / 10;
		}
		if (carry != 0) {
			result.append(carry);
		}
		return result.reverse().toString();
	}

	String appendZeros(String num, int zeroNum) {
		StringBuilder result = new StringBuilder(num);
		for (int i = 0; i < zeroNum; i++) {
			result.append('0');
		}
		return result.toString();
	}

	String add(String num1, String num2) {
		StringBuilder result = new StringBuilder();
		int carry = 0;
		for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
			int sum = (i >= 0 ? num1.charAt(i) - '0' : 0)
					+ (j >= 0 ? num2.charAt(j) - '0' : 0) + carry;
			result.append(sum % 10);
			carry = sum / 10;
		}
		if (carry != 0) {
			result.append(carry);
		}
		return result.reverse().toString();
	}
}
