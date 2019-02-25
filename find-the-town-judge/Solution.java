import java.util.stream.IntStream;

public class Solution {
	public int findJudge(int N, int[][] trust) {
		int[] trusting = new int[N + 1];
		int[] trusted = new int[N + 1];

		for (int[] t : trust) {
			trusting[t[0]]++;
			trusted[t[1]]++;
		}

		int[] candidates = IntStream.rangeClosed(1, N).filter(i -> trusting[i] == 0 && trusted[i] == N - 1).toArray();
		if (candidates.length == 1) {
			return candidates[0];
		} else {
			return -1;
		}
	}
}
