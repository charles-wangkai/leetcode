import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

class Solution {
  public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
    int[] result = new int[queries.length];
    NavigableMap<Integer, Integer> yToSum = new TreeMap<>();
    int[] sortedValueIndices =
        IntStream.range(0, nums1.length)
            .boxed()
            .sorted(Comparator.comparing((Integer i) -> nums1[i]).reversed())
            .mapToInt(Integer::intValue)
            .toArray();
    int sortedIndex = 0;
    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing((Integer i) -> queries[i][0]).reversed())
            .mapToInt(Integer::intValue)
            .toArray();
    for (int queryIndex : sortedQueryIndices) {
      while (sortedIndex != sortedValueIndices.length
          && nums1[sortedValueIndices[sortedIndex]] >= queries[queryIndex][0]) {
        Entry<Integer, Integer> ceiling =
            yToSum.ceilingEntry(nums2[sortedValueIndices[sortedIndex]]);
        int sum = nums1[sortedValueIndices[sortedIndex]] + nums2[sortedValueIndices[sortedIndex]];
        if (ceiling == null || sum >= ceiling.getValue()) {
          yToSum.put(nums2[sortedValueIndices[sortedIndex]], sum);

          while (true) {
            Integer prevY = yToSum.lowerKey(nums2[sortedValueIndices[sortedIndex]]);
            if (prevY == null || yToSum.get(prevY) > sum) {
              break;
            }

            yToSum.remove(prevY);
          }
        }

        ++sortedIndex;
      }

      Entry<Integer, Integer> entry = yToSum.ceilingEntry(queries[queryIndex][1]);
      result[queryIndex] = (entry == null) ? -1 : entry.getValue();
    }

    return result;
  }
}
