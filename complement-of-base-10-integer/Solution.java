public class Solution {
	public int bitwiseComplement(int N) {
		return (1 << Integer.toBinaryString(N).length()) - 1 - N;
	}
}
