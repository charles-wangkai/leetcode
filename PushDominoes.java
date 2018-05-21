import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PushDominoes {
	public String pushDominoes(String dominoes) {
		Side[] leftSides = new Side[dominoes.length()];
		char state = 0;
		int distance = 1;
		for (int i = 0; i < leftSides.length; i++) {
			char ch = dominoes.charAt(i);
			if (ch == '.') {
				distance++;
			} else {
				state = ch;
				distance = 0;
			}

			leftSides[i] = new Side(state, distance);

			distance++;
		}

		Side[] rightSides = new Side[dominoes.length()];
		state = 0;
		distance = 1;
		for (int i = rightSides.length - 1; i >= 0; i--) {
			char ch = dominoes.charAt(i);
			if (ch == '.') {
				distance++;
			} else {
				state = ch;
				distance = 0;
			}

			rightSides[i] = new Side(state, distance);

			distance++;
		}

		return IntStream.range(0, dominoes.length())
				.mapToObj(i -> String.valueOf(computeFinalState(leftSides[i], rightSides[i])))
				.collect(Collectors.joining());
	}

	char computeFinalState(Side leftSide, Side rightSide) {
		if (leftSide.state == 'R') {
			if (rightSide.state == 'L') {
				if (leftSide.distance < rightSide.distance) {
					return 'R';
				} else if (leftSide.distance > rightSide.distance) {
					return 'L';
				} else {
					return '.';
				}
			} else {
				return 'R';
			}
		} else {
			if (rightSide.state == 'L') {
				return 'L';
			} else {
				return '.';
			}
		}
	}
}

class Side {
	char state;
	int distance;

	Side(char state, int distance) {
		this.state = state;
		this.distance = distance;
	}
}