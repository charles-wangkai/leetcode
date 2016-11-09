import java.util.Arrays;

public class MinimumMovesToEqualArrayElements {
	public int minMoves(int[] nums) {
		int min = Arrays.stream(nums).min().getAsInt();
		return Arrays.stream(nums).map(num -> num - min).sum();
	}
}
