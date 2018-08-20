import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
	public int[] fairCandySwap(int[] A, int[] B) {
		int sumA = computeSum(A);
		Set<Integer> numbersA = buildNumbers(A);

		int sumB = computeSum(B);
		Set<Integer> numbersB = buildNumbers(B);

		Iterator<Integer> iterA = numbersA.iterator();
		while (true) {
			int a = iterA.next();
			int b2 = sumB - sumA + a * 2;
			if (b2 % 2 == 0) {
				int b = b2 / 2;
				if (numbersB.contains(b)) {
					return new int[] { a, b };
				}
			}
		}
	}

	int computeSum(int[] x) {
		return Arrays.stream(x).sum();
	}

	Set<Integer> buildNumbers(int[] x) {
		return Arrays.stream(x).boxed().collect(Collectors.toSet());
	}
}
