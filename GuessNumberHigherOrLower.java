/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

class GuessGame {
	int guess(int num) {
		return -1;
	}
}

public class GuessNumberHigherOrLower extends GuessGame {
	public int guessNumber(int n) {
		int lower = 1;
		int upper = n;
		while (true) {
			int middle = (int) (((long) lower + upper) / 2);
			int result = guess(middle);
			if (result == 0) {
				return middle;
			} else if (result < 0) {
				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
	}
}
