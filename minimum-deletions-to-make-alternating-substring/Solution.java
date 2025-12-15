import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] minDeletions(String s, int[][] queries) {
    int blockSize = (int) Math.ceil(Math.sqrt(s.length()));
    int blockNum = Math.ceilDiv(s.length(), blockSize);
    int[] repeatCounts = new int[blockNum];

    char[] letters = s.toCharArray();
    for (int i = 1; i < letters.length; ++i) {
      if (letters[i] == letters[i - 1]) {
        ++repeatCounts[i / blockSize];
      }
    }

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      if (query[0] == 1) {
        int j = query[1];

        if (j != 0) {
          repeatCounts[j / blockSize] += (letters[j] == letters[j - 1]) ? -1 : 1;
        }
        if (j != letters.length - 1) {
          repeatCounts[(j + 1) / blockSize] += (letters[j + 1] == letters[j]) ? -1 : 1;
        }

        letters[j] = (char) ('A' + 'B' - letters[j]);
      } else {
        int l = query[1];
        int r = query[2];

        int deleteNum = 0;
        if (l < r) {
          int begin = l + 1;
          int beginBlockIndex = begin / blockSize;

          int end = r;
          int endBlockIndex = end / blockSize;

          if (beginBlockIndex == endBlockIndex) {
            for (int i = begin; i <= end; ++i) {
              if (letters[i] == letters[i - 1]) {
                ++deleteNum;
              }
            }
          } else {
            for (int blockIndex = beginBlockIndex + 1; blockIndex < endBlockIndex; ++blockIndex) {
              deleteNum += repeatCounts[blockIndex];
            }

            int index = begin;
            while (true) {
              if (letters[index] == letters[index - 1]) {
                ++deleteNum;
              }

              ++index;
              if (index % blockSize == 0) {
                break;
              }
            }

            index = end;
            while (true) {
              if (letters[index] == letters[index - 1]) {
                ++deleteNum;
              }

              if (index % blockSize == 0) {
                break;
              }

              --index;
            }
          }
        }

        result.add(deleteNum);
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}