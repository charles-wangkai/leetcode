// The read4 API is defined in the parent class Reader4.
class Reader4 {
	int read4(char[] buf) {
		throw new UnsupportedOperationException();
	}
}

public class Solution extends Reader4 {
	/**
	 * @param buf
	 *            Destination buffer
	 * @param n
	 *            Maximum number of characters to read
	 * @return The number of characters read
	 */
	public int read(char[] buf, int n) {
		int length = 0;
		char[] temp = new char[4];
		int readNum = 4;
		while (length < n && readNum == 4) {
			readNum = read4(temp);
			for (int i = 0; i < readNum && length < n; i++) {
				buf[length] = temp[i];
				length++;
			}
		}
		return length;
	}
}
