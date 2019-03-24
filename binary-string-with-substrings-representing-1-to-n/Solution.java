public class Solution {
	static final int LIMIT = 1_000_000;

	public boolean queryString(String S, int N) {
		boolean[] visited = new boolean[LIMIT];

		for (int beginIndex = 0; beginIndex < S.length(); beginIndex++) {
			int number = 0;
			for (int endIndex = beginIndex; endIndex < S.length(); endIndex++) {
				number = number * 2 + (S.charAt(endIndex) - '0');

				if (number >= visited.length) {
					break;
				}

				visited[number] = true;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}
}
