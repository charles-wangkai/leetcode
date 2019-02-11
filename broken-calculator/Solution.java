public class Solution {
	public int brokenCalc(int X, int Y) {
		int operationNum = 0;
		while (Y > X) {
			if (Y % 2 != 0) {
				Y++;
				operationNum++;
			}

			Y /= 2;
			operationNum++;
		}
		operationNum += X - Y;

		return operationNum;
	}
}
