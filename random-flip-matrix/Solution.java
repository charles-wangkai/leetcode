import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Solution {
	private Random random = new Random();
	private int col;
	private int totalNum;
	private int availableNum;
	private List<Integer> chosen;

	public Solution(int n_rows, int n_cols) {
		col = n_cols;
		totalNum = n_rows * n_cols;

		reset();
	}

	public int[] flip() {
		int sequence = random.nextInt(availableNum);
		int number = findNumber(sequence);

		chosen.add(number);
		Collections.sort(chosen);

		availableNum--;

		return new int[] { number / col, number % col };
	}

	public void reset() {
		chosen = new ArrayList<>();
		availableNum = totalNum;
	}

	private int findNumber(int sequence) {
		int lower = 0;
		int upper = totalNum - 1;
		while (true) {
			int middle = (lower + upper) / 2;

			int currentSequence = computeSequence(middle);
			if (currentSequence <= sequence) {
				if (currentSequence == sequence && !isChosen(middle)) {
					return middle;
				}

				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
	}

	private boolean isChosen(int number) {
		return Collections.binarySearch(chosen, number) >= 0;
	}

	private int computeSequence(int number) {
		int index = Collections.binarySearch(chosen, number);
		if (index < 0) {
			index = -1 - index;
		}

		return number - index;
	}
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(n_rows, n_cols);
// int[] param_1 = obj.flip();
// obj.reset();
