public class Solution {
	public int maximum69Number(int num) {
		String s = String.valueOf(num);
		int index = s.indexOf('6');
		if (index >= 0) {
			return Integer.parseInt(String.format("%s9%s", s.substring(0, index), s.substring(index + 1)));
		} else {
			return num;
		}
	}
}
