class Solution {
	public int arrangeCoins(int n) {
		int row = (int) Math.floor(Math.sqrt(n * 2L));

		return (row * (row + 1L) <= n * 2L) ? row : (row - 1);
	}
}
