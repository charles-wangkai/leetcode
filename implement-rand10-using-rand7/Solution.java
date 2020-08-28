class SolBase {
	// return a random integer in the range 1 to 7
	int rand7() {
		throw new UnsupportedOperationException();
	}
}

class Solution extends SolBase {
	public int rand10() {
		while (true) {
			int r = (rand7() - 1) * 7 + (rand7() - 1);

			if (r < 40) {
				return r % 10 + 1;
			}
		}
	}
}
