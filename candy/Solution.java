public class Solution {
	int result = 0;
	int level = 0;
	int startIndex = 0;

	public int candy(int[] ratings) {
		for (int i = 0; i < ratings.length; i++) {
			if (i == 0 || ratings[i] == ratings[i - 1]) {
				lowerLevel(i);
				level = 0;
				startIndex = i;
			} else if (ratings[i] < ratings[i - 1]) {
				level--;
				if (level < 0) {
					level = 0;
					result += i - startIndex;
				}
			} else {
				lowerLevel(i);
				level++;
				startIndex = i;
			}
			result += level + 1;
		}
		lowerLevel(ratings.length);
		return result;
	}

	void lowerLevel(int currentIndex) {
		if (currentIndex > startIndex + 1) {
			result -= level * (currentIndex - startIndex - 1);
			level = 0;
		}
	}
}
