import java.util.Arrays;

public class Solution {
	public int[] xorQueries(int[] arr, int[][] queries) {
		int[] prefixXors = new int[arr.length + 1];
		int prefixXor = 0;
		for (int i = 1; i < prefixXors.length; ++i) {
			prefixXor ^= arr[i - 1];
			prefixXors[i] = prefixXor;
		}

		return Arrays.stream(queries).mapToInt(query -> prefixXors[query[1] + 1] ^ prefixXors[query[0]]).toArray();
	}
}
