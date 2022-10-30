import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
    Map<String, List<Integer>> creatorToIndices = new HashMap<>();
    Map<String, Long> creatorToPopularity = new HashMap<>();
    for (int i = 0; i < creators.length; ++i) {
      creatorToIndices.putIfAbsent(creators[i], new ArrayList<>());
      creatorToIndices.get(creators[i]).add(i);

      creatorToPopularity.put(
          creators[i], creatorToPopularity.getOrDefault(creators[i], 0L) + views[i]);
    }

    long maxPopularity = creatorToPopularity.values().stream().mapToLong(x -> x).max().getAsLong();

    return creatorToPopularity.keySet().stream()
        .filter(creator -> creatorToPopularity.get(creator) == maxPopularity)
        .map(
            creator -> {
              int maxView =
                  creatorToIndices.get(creator).stream().mapToInt(i -> views[i]).max().getAsInt();

              return List.of(
                  creator,
                  creatorToIndices.get(creator).stream()
                      .filter(i -> views[i] == maxView)
                      .map(i -> ids[i])
                      .min(Comparator.naturalOrder())
                      .get());
            })
        .toList();
  }
}
