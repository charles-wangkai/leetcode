import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String pushDominoes(String dominoes) {
    Side[] leftSides = new Side[dominoes.length()];
    char state = 0;
    int distance = 0;
    for (int i = 0; i < leftSides.length; ++i) {
      char c = dominoes.charAt(i);
      if (c != '.') {
        state = c;
        distance = 0;
      }
      leftSides[i] = new Side(state, distance);

      ++distance;
    }

    Side[] rightSides = new Side[dominoes.length()];
    state = 0;
    distance = 0;
    for (int i = rightSides.length - 1; i >= 0; --i) {
      char c = dominoes.charAt(i);
      if (c != '.') {
        state = c;
        distance = 0;
      }
      rightSides[i] = new Side(state, distance);

      ++distance;
    }

    return IntStream.range(0, dominoes.length())
        .mapToObj(i -> computeFinalState(leftSides[i], rightSides[i]))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }

  char computeFinalState(Side leftSide, Side rightSide) {
    if (leftSide.state() == 'R') {
      if (rightSide.state() == 'L') {
        if (leftSide.distance() < rightSide.distance()) {
          return 'R';
        } else if (leftSide.distance() > rightSide.distance()) {
          return 'L';
        } else {
          return '.';
        }
      }

      return 'R';
    }

    return (rightSide.state() == 'L') ? 'L' : '.';
  }
}

record Side(char state, int distance) {}
