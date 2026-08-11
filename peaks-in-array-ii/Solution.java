// See hints

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
  public long[] countOfPeaks(int[] nums, int[][] queries) {
    NavigableSet<Integer> peaks = new TreeSet<>();
    for (int i = 1; i < nums.length - 1; ++i) {
      if (nums[i] > Math.max(nums[i - 1], nums[i + 1])) {
        peaks.add(i);
      }
    }

    long[] contribs = new long[nums.length];
    FenwickTree fenwickTree = new FenwickTree(nums.length);

    for (int peak : peaks) {
      updateContrib(contribs, fenwickTree, peaks, peak);
    }

    List<Long> result = new ArrayList<>();
    for (int[] query : queries) {
      if (query[0] == 1) {
        int l = query[1];
        int r = query[2];

        Integer a = peaks.higher(l);
        Integer b = peaks.lower(r);

        long subarrayNum;
        if (a == null || b == null || a > b) {
          subarrayNum = 0;
        } else {
          subarrayNum =
              (long) r * (b - l)
                  + (long) a * (l - findPrev(peaks, a))
                  - (fenwickTree.computePrefixSum(b + 1) - fenwickTree.computePrefixSum(a));
        }

        result.add(subarrayNum);
      } else {
        int index = query[1];
        int value = query[2];

        nums[index] = value;

        for (int i = -1; i <= 1; ++i) {
          int affected = index + i;
          if (affected >= 1 && affected <= nums.length - 2) {
            setContrib(contribs, fenwickTree, affected, 0);

            boolean isPeakNew = nums[affected] > Math.max(nums[affected - 1], nums[affected + 1]);
            boolean isPeakOld = peaks.contains(affected);
            if (isPeakNew != isPeakOld) {
              if (isPeakNew) {
                peaks.add(affected);
              } else {
                peaks.remove(affected);
              }
            }

            if (isPeakNew) {
              updateContrib(contribs, fenwickTree, peaks, affected);
            }
          }
        }

        Integer after = peaks.higher(index + 1);
        if (after != null) {
          updateContrib(contribs, fenwickTree, peaks, after);
        }
      }
    }

    return result.stream().mapToLong(Long::longValue).toArray();
  }

  void updateContrib(
      long[] contribs, FenwickTree fenwickTree, NavigableSet<Integer> peaks, int peak) {
    setContrib(contribs, fenwickTree, peak, (long) peak * (peak - findPrev(peaks, peak)));
  }

  void setContrib(long[] contribs, FenwickTree fenwickTree, int index, long contrib) {
    fenwickTree.add(index + 1, contrib - contribs[index]);
    contribs[index] = contrib;
  }

  int findPrev(NavigableSet<Integer> peaks, int peak) {
    Integer lower = peaks.lower(peak);

    return (lower == null) ? 0 : lower;
  }
}

class FenwickTree {
  long[] a;

  FenwickTree(int size) {
    a = new long[size + 1];
  }

  void add(int pos, long delta) {
    while (pos < a.length) {
      a[pos] += delta;
      pos += pos & -pos;
    }
  }

  long computePrefixSum(int pos) {
    long result = 0;
    while (pos != 0) {
      result += a[pos];
      pos -= pos & -pos;
    }

    return result;
  }
}
