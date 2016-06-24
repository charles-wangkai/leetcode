public class WaterAndJugProblem {
	public boolean canMeasureWater(int x, int y, int z) {
		return z <= x + y && z % gcd(x, y) == 0;
	}

	private int gcd(int a, int b) {
		return (b == 0) ? (a == 0 ? 1 : a) : gcd(b, a % b);
	}
}
