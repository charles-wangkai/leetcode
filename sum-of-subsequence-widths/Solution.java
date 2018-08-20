import java.util.Arrays;

public class Solution {
	static final int MOD_DIVISOR = 1000000007;

	public int sumSubseqWidths(int[] A) {
		Arrays.sort(A);

		int[] pow2 = buildPow2(A.length);

		int result = 0;
		for (int i = 0; i < A.length; i++) {
			result = addMod(result, multiplyMod(subtractMod(pow2[i], pow2[A.length - 1 - i]), A[i]));
		}
		return result;
	}

	int addMod(int x, int y) {
		return (x + y) % MOD_DIVISOR;
	}

	int subtractMod(int x, int y) {
		return (x - y + MOD_DIVISOR) % MOD_DIVISOR;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MOD_DIVISOR);
	}

	int[] buildPow2(int size) {
		int[] pow2 = new int[size];
		int power = 1;
		for (int i = 0; i < pow2.length; i++) {
			pow2[i] = power;

			power = multiplyMod(power, 2);
		}
		return pow2;
	}
}
