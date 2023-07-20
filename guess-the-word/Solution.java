import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

// This is the Master's API interface.
// You should not implement it, or speculate about its implementation
interface Master {
  public int guess(String word);
}

public class Solution {
  public void findSecretWord(String[] wordlist, Master master) {
    List<String> candidates = Arrays.stream(wordlist).toList();
    while (true) {
      int minNextCandidateNum = Integer.MAX_VALUE;
      String wordToGuess = null;

      for (String candidate : candidates) {
        int nextCandidateNum = computeNextCandidateNum(candidates, candidate);

        if (nextCandidateNum < minNextCandidateNum) {
          minNextCandidateNum = nextCandidateNum;
          wordToGuess = candidate;
        }
      }

      int score = master.guess(wordToGuess);
      if (score == wordlist[0].length()) {
        break;
      }

      String word = wordToGuess;
      candidates =
          candidates.stream().filter(candidate -> computeScore(candidate, word) == score).toList();
    }
  }

  int computeNextCandidateNum(List<String> candidates, String word) {
    Map<Integer, Integer> scoreToCount = new HashMap<>();

    for (String candidate : candidates) {
      int score = computeScore(candidate, word);
      scoreToCount.put(score, scoreToCount.getOrDefault(score, 0) + 1);
    }

    return scoreToCount.values().stream().mapToInt(count -> count).max().getAsInt();
  }

  int computeScore(String candidate, String word) {
    return (int)
        IntStream.range(0, candidate.length())
            .filter(i -> candidate.charAt(i) == word.charAt(i))
            .count();
  }
}
