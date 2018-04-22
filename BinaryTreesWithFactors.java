import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreesWithFactors {
	static final int MODULUS = 1000000007;

	public int numFactoredBinaryTrees(int[] A) {
		Arrays.sort(A);

		Map<Integer, Integer> number2index = new HashMap<Integer, Integer>();
		for (int i = 0; i < A.length; i++) {
			number2index.put(A[i], i);
		}

		int[] ways = new int[A.length];
		Arrays.fill(ways, 1);

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < i; j++) {
				if (A[i] % A[j] == 0) {
					int other = A[i] / A[j];
					if (other >= A[j] && number2index.containsKey(other)) {
						int otherIndex = number2index.get(other);

						int addition = multiplyMod(ways[j], ways[otherIndex]);
						if (otherIndex != j) {
							addition = multiplyMod(addition, 2);
						}

						ways[i] = addMod(ways[i], addition);
					}
				}
			}
		}

		int result = 0;
		for (int i = 0; i < ways.length; i++) {
			result = addMod(result, ways[i]);
		}
		return result;
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MODULUS);
	}
}
