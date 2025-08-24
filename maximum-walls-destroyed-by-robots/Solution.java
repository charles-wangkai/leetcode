// https://leetcode.com/problems/maximum-walls-destroyed-by-robots/solutions/7115085/python-dp/

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int maxWalls(int[] robots, int[] distance, int[] walls) {
    int[] sortedIndices =
        IntStream.range(0, robots.length)
            .boxed()
            .sorted(Comparator.comparing(i -> robots[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    Arrays.sort(walls);

    int available = 0;
    int used =
        findWallNum(
            walls,
            robots[sortedIndices[0]] - distance[sortedIndices[0]],
            robots[sortedIndices[0]] - 1);
    for (int i = 0; i < sortedIndices.length; ++i) {
      int l = robots[sortedIndices[i]];
      int dl = distance[sortedIndices[i]];
      int r = (i == sortedIndices.length - 1) ? Integer.MAX_VALUE : robots[sortedIndices[i + 1]];
      int dr = (i == sortedIndices.length - 1) ? 0 : distance[sortedIndices[i + 1]];

      int l1 = l + 1;
      int r1 = Math.min(l + dl, r - 1);
      int l2 = Math.max(r - dr, l + 1);
      int r2 = r - 1;

      int left = findWallNum(walls, l1, r1);
      int right = findWallNum(walls, l2, r2);
      int both = left + right - findWallNum(walls, Math.max(l1, l2), Math.min(r1, r2));

      int nextAvailable = Math.max(available + left, used);
      int nextUsed = Math.max(available + both, used + right);

      available = nextAvailable;
      used = nextUsed;
    }

    return used + Arrays.stream(robots).map(robot -> findWallNum(walls, robot, robot)).sum();
  }

  int findWallNum(int[] walls, int l, int r) {
    int leftIndex = Arrays.binarySearch(walls, l);
    if (leftIndex < 0) {
      leftIndex = -1 - leftIndex;
    }

    int rightIdnex = Arrays.binarySearch(walls, r);
    if (rightIdnex < 0) {
      rightIdnex = -2 - rightIdnex;
    }

    return Math.max(0, rightIdnex - leftIndex + 1);
  }
}
