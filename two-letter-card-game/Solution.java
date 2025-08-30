import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
  public int score(String[] cards, char x) {
    Map<Character, Integer> firstLetterToCount = new HashMap<>();
    Map<Character, Integer> secondLetterToCount = new HashMap<>();
    int bothCount = 0;
    for (String card : cards) {
      char firstLetter = card.charAt(0);
      char secondLetter = card.charAt(1);
      if (firstLetter == x) {
        if (secondLetter == x) {
          ++bothCount;
        } else {
          secondLetterToCount.put(
              secondLetter, secondLetterToCount.getOrDefault(secondLetter, 0) + 1);
        }
      } else if (secondLetter == x) {
        firstLetterToCount.put(firstLetter, firstLetterToCount.getOrDefault(firstLetter, 0) + 1);
      }
    }

    PriorityQueue<Integer> firstCounts = buildCounts(firstLetterToCount);
    int firstTotal = firstCounts.stream().mapToInt(Integer::intValue).sum();

    PriorityQueue<Integer> secondCounts = buildCounts(secondLetterToCount);
    int secondTotal = secondCounts.stream().mapToInt(Integer::intValue).sum();

    int result = 0;
    while (bothCount != 0 && (!firstCounts.isEmpty() || !secondCounts.isEmpty())) {
      if (!firstCounts.isEmpty()
          && computeDiff(firstCounts, firstTotal) >= computeDiff(secondCounts, secondTotal)) {
        int count = firstCounts.poll();
        if (count != 1) {
          firstCounts.offer(count - 1);
        }

        --firstTotal;
      } else {
        int count = secondCounts.poll();
        if (count != 1) {
          secondCounts.offer(count - 1);
        }

        --secondTotal;
      }

      --bothCount;
      ++result;
    }
    result += computeScore(firstCounts, firstTotal) + computeScore(secondCounts, secondTotal);

    return result;
  }

  int computeScore(PriorityQueue<Integer> counts, int total) {
    if (counts.isEmpty()) {
      return 0;
    }

    int other = total - counts.peek();
    if (counts.peek() >= other) {
      return other;
    }

    return total / 2;
  }

  int computeDiff(PriorityQueue<Integer> counts, int total) {
    if (counts.isEmpty()) {
      return 0;
    }

    int other = total - counts.peek();
    if (counts.peek() >= other) {
      return counts.peek() - other;
    }

    return total % 2;
  }

  PriorityQueue<Integer> buildCounts(Map<Character, Integer> letterToCount) {
    PriorityQueue<Integer> result = new PriorityQueue<>(Comparator.reverseOrder());
    for (int count : letterToCount.values()) {
      result.offer(count);
    }

    return result;
  }
}