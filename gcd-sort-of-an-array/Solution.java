import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 100000;
  static final int[] PRIMES = buildPrimes();

  static int[] buildPrimes() {
    boolean[] primes = new boolean[LIMIT + 1];
    Arrays.fill(primes, true);

    for (int i = 2; i < primes.length; ++i) {
      if (primes[i]) {
        for (int j = i * 2; j < primes.length; j += i) {
          primes[j] = false;
        }
      }
    }

    return IntStream.range(2, primes.length).filter(i -> primes[i]).toArray();
  }

  public boolean gcdSort(int[] nums) {
    Map<Integer, List<Integer>> factorToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int rest = nums[i];
      for (int factor : PRIMES) {
        if ((long) factor * factor > rest) {
          break;
        }

        if (rest % factor == 0) {
          if (!factorToIndices.containsKey(factor)) {
            factorToIndices.put(factor, new ArrayList<>());
          }
          factorToIndices.get(factor).add(i);

          while (rest % factor == 0) {
            rest /= factor;
          }
        }
      }
      if (rest != 1) {
        if (!factorToIndices.containsKey(rest)) {
          factorToIndices.put(rest, new ArrayList<>());
        }
        factorToIndices.get(rest).add(i);
      }
    }

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[nums.length];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (List<Integer> indices : factorToIndices.values()) {
      for (int i = 0; i < indices.size() - 1; ++i) {
        int index1 = indices.get(i);
        int index2 = indices.get(i + 1);

        adjLists[index1].add(index2);
        adjLists[index2].add(index1);
      }
    }

    boolean[] visited = new boolean[nums.length];
    for (int i = 0; i < nums.length; ++i) {
      if (!visited[i]) {
        List<Integer> indices = new ArrayList<>();
        search(adjLists, visited, indices, i);
        sort(nums, indices);
      }
    }

    return IntStream.range(0, nums.length - 1).allMatch(i -> nums[i] <= nums[i + 1]);
  }

  void search(List<Integer>[] adjLists, boolean[] visited, List<Integer> indices, int index) {
    visited[index] = true;
    indices.add(index);

    for (int adj : adjLists[index]) {
      if (!visited[adj]) {
        search(adjLists, visited, indices, adj);
      }
    }
  }

  void sort(int[] nums, List<Integer> indices) {
    int[] sorted = indices.stream().map(i -> nums[i]).sorted().mapToInt(x -> x).toArray();
    Collections.sort(indices);
    for (int i = 0; i < indices.size(); ++i) {
      nums[indices.get(i)] = sorted[i];
    }
  }
}
