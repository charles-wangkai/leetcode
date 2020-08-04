class Solution {
	public boolean isPowerOfFour(int num) {
		return num > 0 && 1073741824 % num == 0 && (num & 0x55555555) != 0;
	}
}
