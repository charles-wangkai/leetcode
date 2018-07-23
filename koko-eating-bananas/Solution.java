import java.util.Arrays;

public class Solution {
	public int minEatingSpeed(int[] piles, int H) {
		int result = -1;
		int lower = 1;
		int upper = Arrays.stream(piles).max().getAsInt();
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			if (canEatAll(piles, H, middle)) {
				result = middle;

				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return result;
	}

	boolean canEatAll(int[] piles, int H, int eatSpeed) {
		return Arrays.stream(piles).map(pile -> divideToCeil(pile, eatSpeed)).sum() <= H;
	}

	int divideToCeil(int x, int y) {
		return x / y + (x % y == 0 ? 0 : 1);
	}
}
