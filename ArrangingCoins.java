public class ArrangingCoins {
	public int arrangeCoins(int n) {
		int row = (int) Math.floor(Math.sqrt(n * 2L));
		return ((long) row * (row + 1) <= n * 2L) ? row : (row - 1);
	}
}
