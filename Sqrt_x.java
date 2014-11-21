public class Sqrt_x {
	public int sqrt(int x) {
		int lower = 0;
		int upper = 1 << 16;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			long square = (long) middle * middle;
			if (square == x) {
				return middle;
			} else if (square < x) {
				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
		return upper;
	}
}
