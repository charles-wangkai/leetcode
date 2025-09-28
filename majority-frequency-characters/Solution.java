import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public String majorityFrequencyGroup(String s) {
    Map<Character, Integer> letterToFreq = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToFreq.put(letter, letterToFreq.getOrDefault(letter, 0) + 1);
    }

    Map<Integer, Integer> freqToCount = new HashMap<>();
    for (int freq : letterToFreq.values()) {
      freqToCount.put(freq, freqToCount.getOrDefault(freq, 0) + 1);
    }

    int maxCount = freqToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();
    int freq =
        freqToCount.keySet().stream()
            .filter(f -> freqToCount.get(f) == maxCount)
            .mapToInt(Integer::intValue)
            .max()
            .getAsInt();

    return letterToFreq.keySet().stream()
        .filter(letter -> letterToFreq.get(letter) == freq)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}