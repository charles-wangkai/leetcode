public class CountAndSay {
	public String countAndSay(int n) {
		String number = "1";
		for (int i = 1; i < n; i++) {
			StringBuilder nextNumber = new StringBuilder();
			char current = number.charAt(0);
			int count = 1;
			for (int j = 1; j <= number.length(); j++) {
				if (j < number.length() && number.charAt(j) == current) {
					count++;
				} else {
					nextNumber.append(count);
					nextNumber.append(current);
					if (j != number.length()) {
						current = number.charAt(j);
						count = 1;
					}
				}
			}
			number = nextNumber.toString();
		}
		return number;
	}
}
