import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
  public int[] closestRoom(int[][] rooms, int[][] queries) {
    NavigableSet<Integer> candidates = new TreeSet<>();
    for (int[] room : rooms) {
      candidates.add(room[0]);
    }

    int[] sortedRoomIndices =
        IntStream.range(0, rooms.length)
            .boxed()
            .sorted(Comparator.comparing(i -> rooms[i][1]))
            .mapToInt(x -> x)
            .toArray();
    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i][1]))
            .mapToInt(x -> x)
            .toArray();

    int[] result = new int[queries.length];
    int currentRoomIndex = 0;
    for (int sortedQueryIndex : sortedQueryIndices) {
      while (currentRoomIndex != sortedRoomIndices.length
          && rooms[sortedRoomIndices[currentRoomIndex]][1] < queries[sortedQueryIndex][1]) {
        candidates.remove(rooms[sortedRoomIndices[currentRoomIndex]][0]);
        ++currentRoomIndex;
      }

      Integer lower = candidates.floor(queries[sortedQueryIndex][0]);
      Integer upper = candidates.ceiling(queries[sortedQueryIndex][0]);
      if (lower == null && upper == null) {
        result[sortedQueryIndex] = -1;
      } else if (upper == null
          || (lower != null
              && queries[sortedQueryIndex][0] - lower <= upper - queries[sortedQueryIndex][0])) {
        result[sortedQueryIndex] = lower;
      } else {
        result[sortedQueryIndex] = upper;
      }
    }

    return result;
  }
}
