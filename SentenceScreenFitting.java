import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SentenceScreenFitting {
	public int wordsTyping(String[] sentence, int rows, int cols) {
		int maxWordLength = Arrays.stream(sentence).mapToInt(String::length).max().getAsInt();

		if (maxWordLength > cols) {
			return 0;
		}

		Map<Integer, NextState> wordIndex2nextState = new HashMap<Integer, NextState>();

		int times = 0;
		int wordIndex = 0;
		for (int i = 0; i < rows; i++) {
			int nextWordIndex;
			int timesAddition;

			if (wordIndex2nextState.containsKey(wordIndex)) {
				NextState nextState = wordIndex2nextState.get(wordIndex);

				nextWordIndex = nextState.nextWordIndex;
				timesAddition = nextState.timesAddition;
			} else {
				nextWordIndex = wordIndex;
				timesAddition = 0;

				int c = 0;
				while (cols - c >= sentence[nextWordIndex].length()) {
					c += sentence[nextWordIndex].length() + 1;

					nextWordIndex++;
					if (nextWordIndex == sentence.length) {
						timesAddition++;
						nextWordIndex = 0;
					}
				}

				wordIndex2nextState.put(wordIndex, new NextState(nextWordIndex, timesAddition));
			}

			wordIndex = nextWordIndex;
			times += timesAddition;
		}
		return times;
	}
}

class NextState {
	int nextWordIndex;
	int timesAddition;

	NextState(int nextWordIndex, int timesAddition) {
		this.nextWordIndex = nextWordIndex;
		this.timesAddition = timesAddition;
	}
}