import java.util.stream.IntStream;

class Solution {
  public int minDeletionSize(String[] strs) {
    int result = 0;
    String[] rests = IntStream.range(0, strs.length).mapToObj(i -> "").toArray(String[]::new);
    for (int c = 0; c < strs[0].length(); ++c) {
      String[] rests_ = rests;
      int c_ = c;
      String[] appended =
          IntStream.range(0, rests.length)
              .mapToObj(i -> rests_[i] + strs[i].charAt(c_))
              .toArray(String[]::new);

      if (IntStream.range(0, appended.length - 1)
          .allMatch(i -> appended[i].compareTo(appended[i + 1]) <= 0)) {
        rests = appended;
      } else {
        ++result;
      }
    }

    return result;
  }
}
