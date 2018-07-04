public class Solution {
	public int mirrorReflection(int p, int q) {
		int lcm = computeLCM(p, q);
		if (lcm / p % 2 == 0) {
			return 0;
		} else if (lcm / q % 2 == 0) {
			return 2;
		} else {
			return 1;
		}
	}

	int computeLCM(int a, int b) {
		return a / computeGCD(a, b) * b;
	}

	int computeGCD(int a, int b) {
		return b == 0 ? a : computeGCD(b, a % b);
	}
}
