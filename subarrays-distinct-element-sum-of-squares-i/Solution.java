import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int sumCounts(List<Integer> nums) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < nums.size(); ++beginIndex) {
      for (int endIndex = beginIndex; endIndex < nums.size(); ++endIndex) {
        int distinctCount =
            (int) IntStream.rangeClosed(beginIndex, endIndex).map(nums::get).distinct().count();
        result += distinctCount * distinctCount;
      }
    }

    return result;
  }
}
