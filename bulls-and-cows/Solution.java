import java.util.stream.IntStream;

class Solution {
	public String getHint(String secret, String guess) {
		int[] secretCounts = new int[10];
		int[] guessCounts = new int[10];
		int bull = 0;
		for (int i = 0; i < secret.length(); ++i) {
			int secretDigit = secret.charAt(i) - '0';
			int guessDigit = guess.charAt(i) - '0';
			if (secretDigit == guessDigit) {
				++bull;
			} else {
				++secretCounts[secretDigit];
				++guessCounts[guessDigit];
			}
		}
		int cow = IntStream.range(0, secretCounts.length).map(i -> Math.min(secretCounts[i], guessCounts[i])).sum();

		return String.format("%dA%dB", bull, cow);
	}
}
