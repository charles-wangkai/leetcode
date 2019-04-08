import java.util.Arrays;

public class Solution {
	public int videoStitching(int[][] clips, int T) {
		Arrays.sort(clips, (clip1, clip2) -> Integer.compare(clip1[0], clip2[0]));

		int result = 0;
		int clipIndex = 0;
		int right = 0;
		while (right < T) {
			int nextRight = right;
			while (clipIndex < clips.length && clips[clipIndex][0] <= right) {
				nextRight = Math.max(nextRight, clips[clipIndex][1]);

				clipIndex++;
			}

			if (nextRight == right) {
				return -1;
			}

			right = nextRight;
			result++;
		}
		return result;
	}
}
