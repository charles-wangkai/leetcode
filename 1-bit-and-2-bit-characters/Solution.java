public class Solution {
	public boolean isOneBitCharacter(int[] bits) {
		int index = 0;
		while (true) {
			if (index == bits.length - 1) {
				return true;
			}
			if (index == bits.length) {
				return false;
			}

			if (bits[index] == 0) {
				index++;
			} else {
				index += 2;
			}
		}
	}
}
