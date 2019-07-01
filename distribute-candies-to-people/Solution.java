public class Solution {
	public int[] distributeCandies(int candies, int num_people) {
		int[] result = new int[num_people];
		int index = 0;
		int targetNum = 1;

		while (candies >= targetNum) {
			result[index] += targetNum;
			candies -= targetNum;

			targetNum++;
			index = (index + 1) % result.length;
		}
		result[index] += candies;

		return result;
	}
}
