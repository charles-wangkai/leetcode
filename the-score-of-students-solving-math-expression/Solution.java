import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 1000;

  public int scoreOfStudents(String s, int[] answers) {
    int correct = computeCorrect(s);
    boolean[] possibles = computePossibles(s);

    return Arrays.stream(answers)
        .map(
            answer -> {
              if (answer == correct) {
                return 5;
              } else if (possibles[answer]) {
                return 2;
              }

              return 0;
            })
        .sum();
  }

  int computeCorrect(String s) {
    int result = 0;
    int term = s.charAt(0) - '0';
    for (int i = 1; i < s.length(); i += 2) {
      if (s.charAt(i) == '+') {
        result += term;
        term = s.charAt(i + 1) - '0';
      } else {
        term *= s.charAt(i + 1) - '0';
      }
    }
    result += term;

    return result;
  }

  boolean[] computePossibles(String s) {
    int[] digits = IntStream.range(0, s.length() / 2 + 1).map(i -> s.charAt(i * 2) - '0').toArray();
    Character[] operators =
        IntStream.range(0, s.length() / 2)
            .mapToObj(i -> s.charAt(i * 2 + 1))
            .toArray(Character[]::new);

    boolean[][][] p = new boolean[digits.length][digits.length][LIMIT + 1];
    for (int length = 1; length <= digits.length; ++length) {
      for (int beginIndex = 0; beginIndex + length <= digits.length; ++beginIndex) {
        int endIndex = beginIndex + length - 1;

        if (length == 1) {
          p[beginIndex][endIndex][digits[beginIndex]] = true;
        } else {
          for (int middleIndex = beginIndex; middleIndex < endIndex; ++middleIndex) {
            for (int left = 0; left <= LIMIT; ++left) {
              if (p[beginIndex][middleIndex][left]) {
                if (operators[middleIndex] == '+') {
                  for (int right = 0; left + right <= LIMIT; ++right) {
                    if (p[middleIndex + 1][endIndex][right]) {
                      p[beginIndex][endIndex][left + right] = true;
                    }
                  }
                } else {
                  for (int right = 0; right <= LIMIT && left * right <= LIMIT; ++right) {
                    if (p[middleIndex + 1][endIndex][right]) {
                      p[beginIndex][endIndex][left * right] = true;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    return p[0][digits.length - 1];
  }
}
