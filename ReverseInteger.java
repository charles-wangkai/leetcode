public class ReverseInteger {
	public int reverse(int x) {
		int result = 0;
		while (x != 0) {
			result = result * 10 + x % 10;
			x /= 10;
		}
		return result;
	}
}
