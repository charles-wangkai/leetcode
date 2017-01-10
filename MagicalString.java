public class MagicalString {
	public int magicalString(int n) {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		int digit = 1;
		while (sb.length() < n) {
			int count;
			if (index == 0) {
				count = 1;
			} else if (index == 1) {
				count = 2;
			} else {
				count = sb.charAt(index) - '0';
			}

			for (int i = 0; i < count; i++) {
				sb.append(digit);
			}
			digit = 3 - digit;
			index++;
		}
		return (int) sb.chars().limit(n).filter(ch -> ch == '1').count();
	}
}
