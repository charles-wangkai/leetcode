public class StringCompression {
	public int compress(char[] chars) {
		char prev = 0;
		int count = 0;

		int length = 0;
		for (int i = 0; i <= chars.length; i++) {
			if (i < chars.length && chars[i] == prev) {
				count++;
			} else {
				if (prev != 0) {
					chars[length] = prev;
					length++;
				}
				if (count != 1) {
					int beginIndex = length;
					while (count != 0) {
						chars[length] = (char) (count % 10 + '0');
						length++;

						count /= 10;
					}
					reverse(chars, beginIndex, length - 1);
				}

				if (i < chars.length) {
					prev = chars[i];
					count = 1;
				}
			}
		}
		return length;
	}

	void reverse(char[] chars, int beginIndex, int endIndex) {
		for (int i = beginIndex, j = endIndex; i < j; i++, j--) {
			char temp = chars[i];
			chars[i] = chars[j];
			chars[j] = temp;
		}
	}
}
