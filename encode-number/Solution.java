public class Solution {
	public String encode(int num) {
		if (num == 0) {
			return "";
		}

		return encode((num + 1) / 2 - 1) + ((num % 2 != 0) ? '0' : '1');
	}
}
