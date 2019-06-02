public class Solution {
	public int shortestWay(String source, String target) {
		int result = 0;
		int index = Integer.MAX_VALUE;
		for (char targetCh : target.toCharArray()) {
			while (index < source.length() && source.charAt(index) != targetCh) {
				index++;
			}

			if (index >= source.length()) {
				result++;

				index = 0;
				while (index < source.length() && source.charAt(index) != targetCh) {
					index++;
				}

				if (index == source.length()) {
					return -1;
				}
			}

			index++;
		}
		return result;
	}
}
