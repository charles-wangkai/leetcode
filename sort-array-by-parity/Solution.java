import java.util.Arrays;

class Solution {
	public int[] sortArrayByParity(int[] A) {
		return Arrays.stream(A).boxed().sorted((e1, e2) -> Integer.compare(e1 % 2, e2 % 2)).mapToInt(x -> x).toArray();
	}
}
