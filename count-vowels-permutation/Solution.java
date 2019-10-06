public class Solution {
	static final int MODULUS = 1_000_000_007;

	public int countVowelPermutation(int n) {
		int countA = 1;
		int countE = 1;
		int countI = 1;
		int countO = 1;
		int countU = 1;

		for (int i = 0; i < n - 1; i++) {
			int nextCountA = addMod(addMod(countE, countI), countU);
			int nextCountE = addMod(countA, countI);
			int nextCountI = addMod(countE, countO);
			int nextCountO = countI;
			int nextCountU = addMod(countI, countO);

			countA = nextCountA;
			countE = nextCountE;
			countI = nextCountI;
			countO = nextCountO;
			countU = nextCountU;
		}

		return addMod(addMod(countA, addMod(countE, countI)), addMod(countO, countU));
	}

	static int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}
}
