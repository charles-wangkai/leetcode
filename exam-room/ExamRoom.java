import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class ExamRoom {
  int size;
  NavigableSet<Range> ranges;
  Map<Integer, Range> lowerToRange;
  Map<Integer, Range> upperToRange;
  SortedMap<Score, Range> scoreToRange;

  public ExamRoom(int N) {
    size = N;
    ranges = new TreeSet<>((range1, range2) -> Integer.compare(range1.lower, range2.lower));
    lowerToRange = new HashMap<>();
    upperToRange = new HashMap<>();
    scoreToRange =
        new TreeMap<>(
            (score1, score2) ->
                (score1.minDistance != score2.minDistance)
                    ? Integer.compare(score2.minDistance, score1.minDistance)
                    : Integer.compare(score1.seatIndex, score2.seatIndex));

    addRange(-1, -1);
  }

  public int seat() {
    Score minScore = scoreToRange.firstKey();
    int seatIndex = minScore.seatIndex;

    Range range = scoreToRange.get(minScore);
    removeRange(range);

    addRange(range.lower, seatIndex);
    addRange(seatIndex, range.upper);

    return seatIndex;
  }

  public void leave(int p) {
    Range leftRange = upperToRange.get(p);
    removeRange(leftRange);

    Range rightRange = lowerToRange.get(p);
    removeRange(rightRange);

    addRange(leftRange.lower, rightRange.upper);
  }

  private void addRange(int lower, int upper) {
    Range range = new Range(size, lower, upper);
    ranges.add(range);
    lowerToRange.put(lower, range);
    upperToRange.put(upper, range);
    scoreToRange.put(range.getScore(), range);
  }

  private void removeRange(Range range) {
    ranges.remove(range);
    lowerToRange.remove(range.lower);
    upperToRange.remove(range.upper);
    scoreToRange.remove(range.getScore());
  }
}

class Range {
  int lower;
  int upper;
  Score score;

  Range(int size, int lower, int upper) {
    this.lower = lower;
    this.upper = upper;

    int seatIndex;
    int minDistance;
    if (lower < 0) {
      if (upper < 0) {
        seatIndex = 0;
        minDistance = Integer.MAX_VALUE;
      } else {
        seatIndex = 0;
        minDistance = upper - seatIndex;
      }
    } else {
      if (upper < 0) {
        seatIndex = size - 1;
        minDistance = seatIndex - lower;
      } else {
        seatIndex = (lower + upper) / 2;
        minDistance = Math.min(seatIndex - lower, upper - seatIndex);
      }
    }
    score = new Score(minDistance, seatIndex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lower, upper);
  }

  @Override
  public boolean equals(Object obj) {
    Range other = (Range) obj;
    return lower == other.lower && upper == other.upper;
  }

  public Score getScore() {
    return score;
  }
}

class Score {
  int minDistance;
  int seatIndex;

  Score(int minDistance, int seatIndex) {
    this.minDistance = minDistance;
    this.seatIndex = seatIndex;
  }

  @Override
  public int hashCode() {
    return Objects.hash(minDistance, seatIndex);
  }

  @Override
  public boolean equals(Object obj) {
    Score other = (Score) obj;
    return minDistance == other.minDistance && seatIndex == other.seatIndex;
  }
}

// Your ExamRoom object will be instantiated and called as such:
// ExamRoom obj = new ExamRoom(N);
// int param_1 = obj.seat();
// obj.leave(p);
