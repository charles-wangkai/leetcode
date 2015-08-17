public class AddDigits {
	public int addDigits(int num) {
		if (num == 0) {
			return 0;
		} else {
			return num - (num - 1) / 9 * 9;
		}
	}
}
