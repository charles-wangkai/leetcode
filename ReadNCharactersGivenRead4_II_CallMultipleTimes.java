// The read4 API is defined in the parent class Reader4.
class Reader4 {
	int read4(char[] buf) {
		throw new UnsupportedOperationException();
	}
}

public class ReadNCharactersGivenRead4_II_CallMultipleTimes extends Reader4 {
	char[] temp = new char[4];
	int readNum = temp.length;
	int tempIndex = temp.length;
	boolean read4End = false;

	/**
	 * @param buf
	 *            Destination buffer
	 * @param n
	 *            Maximum number of characters to read
	 * @return The number of characters read
	 */
	public int read(char[] buf, int n) {
		int length = 0;
		while (length < n) {
			if (tempIndex == readNum) {
				if (read4End) {
					break;
				}
				readNum = read4(temp);
				if (readNum < 4) {
					read4End = true;
				}
				tempIndex = 0;
				continue;
			}
			buf[length] = temp[tempIndex];
			length++;
			tempIndex++;
		}
		return length;
	}
}
