import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Solution {
	static final int PLACE_VALUE = 27;
	static final int MODULUS = 1_000_000_007;

	public int distinctEchoSubstrings(String text) {
		int n = text.length();

		int[][] hashes = new int[n][n];
		for (int i = 0; i < n; ++i) {
			int hash = 0;
			for (int j = i; j < n; ++j) {
				hash = addMod(multiplyMod(hash, PLACE_VALUE), text.charAt(j) - 'a' + 1);

				hashes[i][j] = hash;
			}
		}

		Set<Integer> unitHashes = new HashSet<>();
		for (int i = 0; i < n; ++i) {
			for (int j = i; j < n && j + j - i + 1 < n; ++j) {
				if (hashes[i][j] == hashes[j + 1][j + j - i + 1]) {
					unitHashes.add(hashes[i][j]);
				}
			}
		}

		return unitHashes.size();
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MODULUS);
	}
}

class Element {
	int length;
	long hash;

	Element(int length, long hash) {
		this.length = length;
		this.hash = hash;
	}

	@Override
	public int hashCode() {
		return Objects.hash(length, hash);
	}

	@Override
	public boolean equals(Object obj) {
		Element other = (Element) obj;
		return length == other.length && hash == other.hash;
	}
}