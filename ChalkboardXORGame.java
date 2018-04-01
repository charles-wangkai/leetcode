import java.util.Arrays;

public class ChalkboardXORGame {
	public boolean xorGame(int[] nums) {
		return Arrays.stream(nums).reduce((x, y) -> x ^ y).getAsInt() == 0 || nums.length % 2 == 0;
	}
}
