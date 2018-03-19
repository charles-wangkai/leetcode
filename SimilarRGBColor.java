public class SimilarRGBColor {
	public String similarRGB(String color) {
		int maxSimilarity = Integer.MIN_VALUE;
		String result = null;
		for (int r = 0; r < 16; r++) {
			for (int g = 0; g < 16; g++) {
				for (int b = 0; b < 16; b++) {
					int similarity = computeSimilarity(color, r, g, b);

					if (similarity > maxSimilarity) {
						maxSimilarity = similarity;
						result = String.format("#%s%s%s%s%s%s", Integer.toHexString(r), Integer.toHexString(r),
								Integer.toHexString(g), Integer.toHexString(g), Integer.toHexString(b),
								Integer.toHexString(b));
					}
				}
			}
		}
		return result;
	}

	int computeSimilarity(String color, int r, int g, int b) {
		return -square(Integer.parseInt(color.substring(1, 3), 16) - (r * 16 + r))
				- square(Integer.parseInt(color.substring(3, 5), 16) - (g * 16 + g))
				- square(Integer.parseInt(color.substring(5, 7), 16) - (b * 16 + b));
	}

	int square(int n) {
		return n * n;
	}
}
