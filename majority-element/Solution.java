public class Solution {
	public int majorityElement(int[] num) {
		int majority = 0;
		int count = 0;
		for (int oneNum : num) {
			if (count == 0) {
				majority = oneNum;
			}
			if (oneNum == majority) {
				count++;
			} else {
				count--;
			}
		}
		return majority;
	}
}
