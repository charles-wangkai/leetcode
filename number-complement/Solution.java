public class Solution {
	public int findComplement(int num) {
		return (1 << Integer.toBinaryString(num).length()) - 1 - num;
	}
}
