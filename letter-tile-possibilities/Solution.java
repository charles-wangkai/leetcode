import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	public int numTilePossibilities(String tiles) {
		Set<String> sequences = new HashSet<>();
		search(sequences, tiles.toCharArray(), 0);
		return sequences.size();
	}

	void search(Set<String> sequences, char[] letters, int index) {
		if (index == letters.length) {
			return;
		}

		for (int i = index; i < letters.length; i++) {
			swap(letters, i, index);
			sequences.add(new String(Arrays.copyOf(letters, index + 1)));

			search(sequences, letters, index + 1);

			swap(letters, i, index);
		}
	}

	void swap(char[] a, int index1, int index2) {
		char temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
}
