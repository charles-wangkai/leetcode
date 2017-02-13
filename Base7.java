public class Base7 {
	public String convertToBase7(int num) {
		if (num == 0) {
			return "0";
		}
		if (num < 0) {
			return "-" + convertToBase7(-num);
		}

		String result = "";
		while (num != 0) {
			result = num % 7 + result;

			num /= 7;
		}
		return result;
	}
}
