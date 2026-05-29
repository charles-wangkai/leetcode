import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int[] numberOfPairs(int[] nums1, int[] nums2, int[][] queries) {
    int blockSize = (int) Math.ceil(Math.sqrt(nums2.length));
    int blockNum = Math.ceilDiv(nums2.length, blockSize);

    int[] deltas = new int[blockNum];

    @SuppressWarnings("unchecked")
    Map<Integer, Integer>[] blockMaps = new Map[blockNum];
    for (int i = 0; i < blockMaps.length; ++i) {
      blockMaps[i] = new HashMap<>();
    }
    for (int block = 0; block < blockNum; ++block) {
      rebuildBlockMap(nums2, blockSize, blockMaps, block);
    }

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      if (query[0] == 1) {
        int x = query[1];
        int y = query[2];
        int val = query[3];

        int beginBlock = x / blockSize;
        int endBlock = y / blockSize;
        if (beginBlock == endBlock) {
          pushBlock(nums2, blockSize, deltas, beginBlock);
          for (int i = x; i <= y; ++i) {
            nums2[i] += val;
          }
          rebuildBlockMap(nums2, blockSize, blockMaps, beginBlock);
        } else {
          pushBlock(nums2, blockSize, deltas, beginBlock);
          for (int i = x; i < (beginBlock + 1) * blockSize; ++i) {
            nums2[i] += val;
          }
          rebuildBlockMap(nums2, blockSize, blockMaps, beginBlock);

          pushBlock(nums2, blockSize, deltas, endBlock);
          for (int i = endBlock * blockSize; i <= y; ++i) {
            nums2[i] += val;
          }
          rebuildBlockMap(nums2, blockSize, blockMaps, endBlock);

          for (int block = beginBlock + 1; block <= endBlock - 1; ++block) {
            deltas[block] += val;
          }
        }
      } else {
        int tot = query[1];

        result.add(
            Arrays.stream(nums1).map(value1 -> computeFreq(tot - value1, deltas, blockMaps)).sum());
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  void pushBlock(int[] nums2, int blockSize, int[] deltas, int block) {
    int beginIndex = block * blockSize;
    int endIndex = Math.min(nums2.length - 1, beginIndex + blockSize - 1);

    for (int i = beginIndex; i <= endIndex; ++i) {
      nums2[i] += deltas[block];
    }

    deltas[block] = 0;
  }

  void rebuildBlockMap(int[] nums2, int blockSize, Map<Integer, Integer>[] blockMaps, int block) {
    int beginIndex = block * blockSize;
    int endIndex = Math.min(nums2.length - 1, beginIndex + blockSize - 1);

    blockMaps[block].clear();
    for (int i = beginIndex; i <= endIndex; ++i) {
      blockMaps[block].put(nums2[i], blockMaps[block].getOrDefault(nums2[i], 0) + 1);
    }
  }

  int computeFreq(int target, int[] deltas, Map<Integer, Integer>[] blockMaps) {
    int result = 0;
    for (int block = 0; block < deltas.length; ++block) {
      result += blockMaps[block].getOrDefault(target - deltas[block], 0);
    }

    return result;
  }
}
