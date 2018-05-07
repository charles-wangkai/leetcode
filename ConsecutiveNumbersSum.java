public class ConsecutiveNumbersSum {
	public int consecutiveNumbersSum(int N) {
		int product = N * 2;
		int result = 0;
		for (int smallerFactor = 1; smallerFactor * smallerFactor < product; smallerFactor++) {
			if (product % smallerFactor == 0) {
				int largerFactor = product / smallerFactor;

				int sum = smallerFactor + largerFactor;
				if (sum % 2 != 0) {
					int end = (sum - 1) / 2;
					int begin = largerFactor - end;

					if (begin > 0 && end > 0) {
						result++;
					}
				}
			}
		}
		return result;
	}
}
