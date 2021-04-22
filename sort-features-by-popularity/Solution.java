import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public String[] sortFeatures(String[] features, String[] responses) {
    Map<String, Integer> featureToCount = new HashMap<>();
    for (String response : responses) {
      for (String feature : Arrays.stream(response.split(" ")).distinct().toArray(String[]::new)) {
        featureToCount.put(feature, featureToCount.getOrDefault(feature, 0) + 1);
      }
    }

    return IntStream.range(0, features.length)
        .boxed()
        .sorted(
            Comparator.comparing((Integer i) -> featureToCount.getOrDefault(features[i], 0))
                .reversed()
                .thenComparing(i -> i))
        .map(i -> features[i])
        .toArray(String[]::new);
  }
}
