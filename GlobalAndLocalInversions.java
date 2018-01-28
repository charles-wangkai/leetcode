import java.util.stream.IntStream;

public class GlobalAndLocalInversions {
	public boolean isIdealPermutation(int[] A) {
		return IntStream.range(0, A.length).allMatch(i -> Math.abs(A[i] - i) <= 1);
	}
}
