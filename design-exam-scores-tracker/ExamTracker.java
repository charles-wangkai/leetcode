import java.util.ArrayList;
import java.util.List;

class ExamTracker {
  List<Element> elements = new ArrayList<>();

  public void record(int time, int score) {
    elements.add(
        new Element(time, (elements.isEmpty() ? 0 : elements.getLast().prefixSum()) + score));
  }

  public long totalScore(int startTime, int endTime) {
    int beginIndex = findBeginIndex(startTime);
    int endIndex = findEndIndex(endTime);

    return (beginIndex <= endIndex)
        ? (elements.get(endIndex).prefixSum()
            - ((beginIndex == 0) ? 0 : elements.get(beginIndex - 1).prefixSum()))
        : 0;
  }

  int findBeginIndex(int startTime) {
    int result = elements.size();
    int lower = 0;
    int upper = elements.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (elements.get(middle).time() >= startTime) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  int findEndIndex(int endTime) {
    int result = -1;
    int lower = 0;
    int upper = elements.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (elements.get(middle).time() <= endTime) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}

record Element(int time, long prefixSum) {}

// Your ExamTracker object will be instantiated and called as such:
// ExamTracker obj = new ExamTracker();
// obj.record(time,score);
// long param_2 = obj.totalScore(startTime,endTime);
