import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int minDeletions(String s) {
    int[] freqs = new int[26];
    for (char ch : s.toCharArray()) {
      ++freqs[ch - 'a'];
    }

    SortedMap<Integer, Integer> freqToCount = new TreeMap<>();
    for (int freq : freqs) {
      if (freq != 0) {
        freqToCount.put(freq, freqToCount.getOrDefault(freq, 0) + 1);
      }
    }

    int result = 0;
    for (int freq : List.copyOf(freqToCount.keySet())) {
      while (freqToCount.get(freq) >= 2) {
        for (int f = freq; f >= 1 && freqToCount.getOrDefault(f, 0) >= 2; --f) {
          freqToCount.put(f, freqToCount.get(f) - 1);
          if (f != 1) {
            freqToCount.put(f - 1, freqToCount.getOrDefault(f - 1, 0) + 1);
          }

          ++result;
        }
      }
    }

    return result;
  }
}
