public class UTF8Validation {
	public boolean validUtf8(int[] data) {
		int followNum = 0;
		for (int part : data) {
			String binary = toByteString(part);

			if (followNum > 0) {
				if (!binary.startsWith("10")) {
					return false;
				}

				followNum--;
			} else {
				int leadingOneNum = countLeadingOnes(binary);

				if (leadingOneNum == 1 || leadingOneNum > 4) {
					return false;
				}

				if (leadingOneNum >= 2) {
					followNum = leadingOneNum - 1;
				}
			}
		}

		if (followNum > 0) {
			return false;
		}

		return true;
	}

	String toByteString(int n) {
		return String.format("%8s", Integer.toBinaryString(n)).replace(' ', '0');
	}

	int countLeadingOnes(String binary) {
		int leadingOneNum = 0;
		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i) != '1') {
				break;
			}
			leadingOneNum++;
		}
		return leadingOneNum;
	}
}
