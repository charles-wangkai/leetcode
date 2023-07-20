import java.util.Arrays;
import java.util.Comparator;

public class Solution {
  public String lastSubstring(String s) {
    int[] suffixArray = buildSuffixArray(s);

    return s.substring(suffixArray[suffixArray.length - 1]);
  }

  int[] buildSuffixArray(String s) {
    int n = s.length();

    Comparator<Suffix> comparator =
        (s1, s2) ->
            (s1.rank != s2.rank)
                ? Integer.compare(s1.rank, s2.rank)
                : Integer.compare(s1.nextRank, s2.nextRank);

    Suffix[] suffixes = new Suffix[n];
    for (int i = 0; i < suffixes.length; i++) {
      int rank = s.charAt(i);
      int nextRank = (i + 1 == n) ? -1 : s.charAt(i + 1);

      suffixes[i] = new Suffix(i, rank, nextRank);
    }

    Arrays.sort(suffixes, comparator);

    for (int k = 4; k < 2 * n; k *= 2) {
      int[] indexToSuffixIndex = new int[n];
      for (int i = 0; i < indexToSuffixIndex.length; i++) {
        indexToSuffixIndex[suffixes[i].index] = i;
      }

      Suffix[] nextSuffixes = new Suffix[n];
      for (int i = 0; i < nextSuffixes.length; i++) {
        int rank;
        if (i == 0) {
          rank = 0;
        } else if (suffixes[i].rank == suffixes[i - 1].rank
            && suffixes[i].nextRank == suffixes[i - 1].nextRank) {
          rank = nextSuffixes[i - 1].rank;
        } else {
          rank = nextSuffixes[i - 1].rank + 1;
        }

        nextSuffixes[i] = new Suffix(suffixes[i].index, rank, -2);
      }
      for (int i = 0; i < nextSuffixes.length; i++) {
        int nextIndex = nextSuffixes[i].index + k / 2;
        nextSuffixes[i].nextRank =
            (nextIndex < n) ? nextSuffixes[indexToSuffixIndex[nextIndex]].rank : -1;
      }

      suffixes = nextSuffixes;
      Arrays.sort(suffixes, comparator);

      if (suffixes[suffixes.length - 1].rank == n - 1) {
        break;
      }
    }

    return Arrays.stream(suffixes).mapToInt(suffix -> suffix.index).toArray();
  }
}

class Suffix {
  int index;
  int rank;
  int nextRank;

  Suffix(int index, int rank, int nextRank) {
    this.index = index;
    this.rank = rank;
    this.nextRank = nextRank;
  }
}
