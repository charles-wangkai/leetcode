public class PerfectNumber {
	public boolean checkPerfectNumber(int num) {
		if (num == 1) {
			return false;
		}

		int divisorSum = 1;
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				divisorSum += i;
				if (i * i != num) {
					divisorSum += num / i;
				}
			}
		}
		return divisorSum == num;
	}
}
