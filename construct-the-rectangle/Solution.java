public class Solution {
	public int[] constructRectangle(int area) {
		int[] result = null;
		for (int W = 1; W * W <= area; W++) {
			if (area % W == 0) {
				result = new int[] { area / W, W };
			}
		}
		return result;
	}
}
