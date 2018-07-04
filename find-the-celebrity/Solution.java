// The knows API is defined in the parent class Relation.
class Relation {
	boolean knows(int a, int b) {
		throw new UnsupportedOperationException();
	}
}

public class Solution extends Relation {
	public int findCelebrity(int n) {
		Boolean[][] relations = new Boolean[n][n];
		for (int i = 0; i < n; i++) {
			if (isCelebrity(relations, i)) {
				return i;
			}
		}
		return -1;
	}

	boolean isCelebrity(Boolean[][] relations, int person) {
		int n = relations.length;
		for (int i = 0; i < n; i++) {
			if (i == person) {
				continue;
			}

			if (relations[i][person] == null) {
				relations[i][person] = knows(i, person);
			}
			if (!relations[i][person]) {
				return false;
			}

			if (relations[person][i] == null) {
				relations[person][i] = knows(person, i);
			}
			if (relations[person][i]) {
				return false;
			}
		}
		return true;
	}
}
