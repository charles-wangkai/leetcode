public class Candy {
	public int candy(int[] ratings) {
		int result = 0;
		int level = 0;
		int startIndex = 0;
		for (int i = 0; i < ratings.length; i++) {
			if (i == 0 || ratings[i] == ratings[i - 1]) {
				result -= level * (i - startIndex - 1);
				level = 0;
				startIndex = i;
			} else if (ratings[i] < ratings[i - 1]) {
				level--;
				if (level < 0) {
					level = 0;
					result += i - startIndex;
				}
			} else {
				if (i > startIndex + 1) {
					result -= level * (i - startIndex - 1);
					level = 0;
				}
				level++;
				startIndex = i;
			}
			result += level + 1;
		}
		result -= level * (ratings.length - startIndex - 1);
		return result;
	}
}
