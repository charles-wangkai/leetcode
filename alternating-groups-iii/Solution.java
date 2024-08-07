// https://leetcode.com/problems/alternating-groups-iii/solutions/5588249/python-solution-store-all-end-indexes-counter-segment-size/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
  public List<Integer> numberOfAlternatingGroups(int[] colors, int[][] queries) {
    NavigableSet<Integer> bounds = new TreeSet<>();
    for (int i = 0; i < colors.length; ++i) {
      if (colors[i] == colors[(i + 1) % colors.length]) {
        bounds.add(i);
      }
    }

    Map<Integer, Integer> sizeToCount = buildSizeToCount(colors, bounds);

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      if (query[0] == 1) {
        Map<Integer, Integer> sizeToCount_ = sizeToCount;
        result.add(
            bounds.isEmpty()
                ? colors.length
                : sizeToCount.keySet().stream()
                    .filter(size -> size >= query[1])
                    .mapToInt(size -> (size - query[1] + 1) * sizeToCount_.get(size))
                    .sum());
      } else if (colors[query[1]] != query[2]) {
        colors[query[1]] = query[2];

        int prev = Math.floorMod(query[1] - 1, colors.length);
        if (bounds.size() < 5) {
          toggle(bounds, query[1]);
          toggle(bounds, prev);

          sizeToCount = buildSizeToCount(colors, bounds);
        } else {
          int high = getBoundAfter(bounds, query[1]);
          int low = getBoundBefore(bounds, prev);
          if (bounds.contains(query[1])) {
            if (bounds.contains(prev)) {
              updateMap(sizeToCount, toSize(colors, prev - low), -1);
              updateMap(sizeToCount, 1, -1);
              updateMap(sizeToCount, toSize(colors, high - query[1]), -1);
              updateMap(sizeToCount, toSize(colors, high - low), 1);
            } else {
              updateMap(sizeToCount, toSize(colors, query[1] - low), -1);
              updateMap(sizeToCount, toSize(colors, high - query[1]), -1);
              updateMap(sizeToCount, toSize(colors, prev - low), 1);
              updateMap(sizeToCount, toSize(colors, high - prev), 1);
            }
          } else if (bounds.contains(prev)) {
            updateMap(sizeToCount, toSize(colors, prev - low), -1);
            updateMap(sizeToCount, toSize(colors, high - prev), -1);
            updateMap(sizeToCount, toSize(colors, query[1] - low), 1);
            updateMap(sizeToCount, toSize(colors, high - query[1]), 1);
          } else {
            updateMap(sizeToCount, toSize(colors, high - low), -1);
            updateMap(sizeToCount, toSize(colors, prev - low), 1);
            updateMap(sizeToCount, 1, 1);
            updateMap(sizeToCount, toSize(colors, high - query[1]), 1);
          }

          toggle(bounds, query[1]);
          toggle(bounds, prev);
        }
      }
    }

    return result;
  }

  void toggle(NavigableSet<Integer> bounds, int bound) {
    if (bounds.contains(bound)) {
      bounds.remove(bound);
    } else {
      bounds.add(bound);
    }
  }

  Map<Integer, Integer> buildSizeToCount(int[] colors, NavigableSet<Integer> bounds) {
    Map<Integer, Integer> sizeToCount = new HashMap<>();
    if (!bounds.isEmpty()) {
      int prev = getBoundBefore(bounds, bounds.first());
      for (int bound : bounds) {
        updateMap(sizeToCount, toSize(colors, bound - prev), 1);
        prev = bound;
      }
    }

    return sizeToCount;
  }

  void updateMap(Map<Integer, Integer> sizeToCount, int size, int delta) {
    sizeToCount.put(size, sizeToCount.getOrDefault(size, 0) + delta);
    sizeToCount.remove(size, 0);
  }

  int toSize(int[] colors, int diff) {
    if (diff <= 0) {
      return diff + colors.length;
    }
    if (diff > colors.length) {
      return diff - colors.length;
    }

    return diff;
  }

  int getBoundBefore(NavigableSet<Integer> bounds, int bound) {
    Integer lower = bounds.lower(bound);

    return (lower == null) ? bounds.last() : lower;
  }

  int getBoundAfter(NavigableSet<Integer> bounds, int bound) {
    Integer higher = bounds.higher(bound);

    return (higher == null) ? bounds.first() : higher;
  }
}