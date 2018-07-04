public class Solution {
	public int findComplement(int num) {
		int complement = 0;
		int base = 1;
		while (num != 0) {
			int digit = num & 1;

			complement += (1 - digit) * base;

			num >>= 1;
			base <<= 1;
		}
		return complement;
	}
}
