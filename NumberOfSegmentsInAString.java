public class NumberOfSegmentsInAString {
	public int countSegments(String s) {
		int segmentNum = 0;
		boolean prevSpace = true;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				prevSpace = true;
			} else {
				if (prevSpace) {
					segmentNum++;
				}

				prevSpace = false;
			}
		}
		return segmentNum;
	}
}
