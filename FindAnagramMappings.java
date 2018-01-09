public class FindAnagramMappings {
	public int[] anagramMappings(int[] A, int[] B) {
		int[] P = new int[A.length];
		for (int i = 0; i < P.length; i++) {
			for (int j = 0; j < B.length; j++) {
				if (B[j] == A[i]) {
					P[i] = j;
					break;
				}
			}
		}
		return P;
	}
}
