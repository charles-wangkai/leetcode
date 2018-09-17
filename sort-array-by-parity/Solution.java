import java.util.Arrays;

public class Solution {
	public int[] sortArrayByParity(int[] A) {
		return Arrays.stream(A).boxed().sorted((elem1, elem2) -> Integer.compare(elem1 % 2, elem2 % 2)).mapToInt(x -> x)
				.toArray();
	}
}
