import java.util.Arrays;
import java.util.Random;

public class RandomPickWithBlacklist {
	private static Random random = new Random();

	int N;
	int[] blacklist;

	public RandomPickWithBlacklist(int N, int[] blacklist) {
		this.N = N;
		this.blacklist = Arrays.stream(blacklist).sorted().toArray();
	}

	public int pick() {
		int targetIndex = random.nextInt(N - blacklist.length);

		int lower = 0;
		int upper = N - 1;
		while (true) {
			int middle = (lower + upper) / 2;

			int blacklistPos = Arrays.binarySearch(blacklist, middle);
			int blackCount;
			if (blacklistPos >= 0) {
				blackCount = blacklistPos + 1;
			} else {
				blackCount = -1 - blacklistPos;
			}

			int currentIndex = middle - blackCount;
			if (blacklistPos < 0 && currentIndex == targetIndex) {
				return middle;
			}

			if (currentIndex < targetIndex) {
				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
	}
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(N, blacklist); int param_1 = obj.pick();
 */