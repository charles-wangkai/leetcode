import java.util.List;

class Solution {
  public int maxDistance(List<List<Integer>> arrays) {
    int[] sortedMins = arrays.stream().mapToInt(array -> array.get(0)).sorted().toArray();

    return arrays.stream()
        .mapToInt(
            array ->
                array.get(array.size() - 1)
                    - ((array.get(0) == sortedMins[0]) ? sortedMins[1] : sortedMins[0]))
        .max()
        .getAsInt();
  }
}
