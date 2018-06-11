public class ShiftingLetters {
	static final int MODULUS = 26;

	public String shiftingLetters(String S, int[] shifts) {
		int currentShift = 0;
		for (int shift : shifts) {
			currentShift = addMod(currentShift, shift);
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < S.length(); i++) {
			result.append((char) (addMod(S.charAt(i) - 'a', currentShift) + 'a'));

			currentShift = addMod(currentShift, -shifts[i]);
		}
		return result.toString();
	}

	int addMod(int x, int y) {
		return (x + y % MODULUS + MODULUS) % MODULUS;
	}
}
