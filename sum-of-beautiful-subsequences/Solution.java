// https://leetcode.com/problems/sum-of-beautiful-subsequences/solutions/7140297/python-sieve/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int totalBeauty(int[] nums) {
    int maxValue = Arrays.stream(nums).max().getAsInt();

    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    int[] f = new int[maxValue + 1];
    for (int d = 1; d < f.length; ++d) {
      List<Integer> indices = new ArrayList<>();
      for (int i = d; i <= maxValue; i += d) {
        indices.addAll(valueToIndices.getOrDefault(i, List.of()));
      }
      Collections.sort(indices);

      if (indices.size() <= 1) {
        f[d] = indices.size();
      } else {
        Map<Integer, Integer> indexToRank =
            IntStream.range(0, indices.size())
                .boxed()
                .collect(Collectors.toMap(indices::get, i -> i + 1));

        int[] binaryIndexedTree = new int[Integer.highestOneBit(indices.size()) * 2 + 1];
        for (int i = d; i <= maxValue; i += d) {
          for (int index : valueToIndices.getOrDefault(i, List.of()).reversed()) {
            int rank = indexToRank.get(index);
            int addend = 1 + query(binaryIndexedTree, rank - 1);
            f[d] = addMod(f[d], addend);
            update(binaryIndexedTree, rank, addend);
          }
        }
      }
    }

    for (int d = f.length - 1; d >= 1; --d) {
      for (int i = 2 * d; i < f.length; i += d) {
        f[d] = addMod(f[d], -f[i]);
      }
    }

    return IntStream.rangeClosed(1, maxValue)
        .map(d -> multiplyMod(d, f[d]))
        .reduce(this::addMod)
        .getAsInt();
  }

  int query(int[] binaryIndexedTree, int index) {
    int result = 0;
    while (index != 0) {
      result = addMod(result, binaryIndexedTree[index]);
      index -= index & -index;
    }

    return result;
  }

  void update(int[] binaryIndexedTree, int index, int delta) {
    while (index < binaryIndexedTree.length) {
      binaryIndexedTree[index] = addMod(binaryIndexedTree[index], delta);
      index += index & -index;
    }
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
