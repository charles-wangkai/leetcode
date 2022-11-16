// Forward declaration of guess API.
// @param  num   your guess
// @return 	     -1 if num is higher than the picked number
//			          1 if num is lower than the picked number
//               otherwise return 0
// int guess(int num);

class GuessGame {
  int guess(int num) {
    throw new UnsupportedOperationException();
  }
}

public class Solution extends GuessGame {
  public int guessNumber(int n) {
    int lower = 1;
    int upper = n;
    while (true) {
      int middle = lower + (upper - lower) / 2;

      int resp = guess(middle);
      if (resp == -1) {
        upper = middle - 1;
      } else if (resp == 1) {
        lower = middle + 1;
      } else {
        return middle;
      }
    }
  }
}
