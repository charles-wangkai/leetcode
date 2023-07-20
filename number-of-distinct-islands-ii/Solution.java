import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int numDistinctIslands2(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    Set<Island> islands = new HashSet<>();
    boolean[][] visited = new boolean[row][col];
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < col; c++) {
        List<Point> lands = new ArrayList<>();
        search(grid, visited, lands, r, c);
        if (!lands.isEmpty()) {
          islands.add(new Island(lands));
        }
      }
    }
    return islands.size();
  }

  void search(int[][] grid, boolean[][] visited, List<Point> lands, int r, int c) {
    int row = grid.length;
    int col = grid[0].length;

    if (!(r >= 0 && r < row && c >= 0 && c < col) || visited[r][c] || grid[r][c] == 0) {
      return;
    }

    visited[r][c] = true;
    lands.add(new Point(r, c));
    for (int i = 0; i < R_OFFSETS.length; i++) {
      search(grid, visited, lands, r + R_OFFSETS[i], c + C_OFFSETS[i]);
    }
  }
}

class Island {
  static final Transformation[] TRANSFORMATIONS = {
    (maxR, maxC, land) -> land,
    (maxR, maxC, land) -> new Point(land.c, maxR - land.r),
    (maxR, maxC, land) -> new Point(maxR - land.r, maxC - land.c),
    (maxR, maxC, land) -> new Point(maxC - land.c, land.r),
    (maxR, maxC, land) -> new Point(land.r, maxC - land.c),
    (maxR, maxC, land) -> new Point(maxR - land.r, land.c),
    (maxR, maxC, land) -> new Point(land.c, land.r),
    (maxR, maxC, land) -> new Point(maxC - land.c, maxR - land.r)
  };

  List<Point> lands;

  Island(List<Point> lands) {
    this.lands = lands;

    Collections.sort(
        this.lands,
        (p1, p2) -> (p1.r != p2.r) ? Integer.compare(p1.r, p2.r) : Integer.compare(p1.c, p2.c));

    int rOrigin = this.lands.get(0).r;
    int cOrigin = this.lands.get(0).c;

    this.lands.stream()
        .forEach(
            p -> {
              p.r -= rOrigin;
              p.c -= cOrigin;
            });
  }

  @Override
  public int hashCode() {
    return lands.size();
  }

  @Override
  public boolean equals(Object obj) {
    return Arrays.stream(TRANSFORMATIONS)
        .anyMatch(transformation -> isSame(transformation.transform((Island) obj)));
  }

  boolean isSame(Island other) {
    return lands.size() == other.lands.size()
        && IntStream.range(0, lands.size()).allMatch(i -> lands.get(i).equals(other.lands.get(i)));
  }
}

@FunctionalInterface
interface Transformation {
  Point transform(int maxR, int maxC, Point land);

  default Island transform(Island island) {
    int maxR = island.lands.stream().mapToInt(land -> land.r).max().getAsInt();
    int maxC = island.lands.stream().mapToInt(land -> land.c).max().getAsInt();

    return new Island(
        island.lands.stream()
            .map(land -> transform(maxR, maxC, land))
            .collect(Collectors.toList()));
  }
}

class Point {
  int r;
  int c;

  Point(int r, int c) {
    this.r = r;
    this.c = c;
  }

  @Override
  public int hashCode() {
    return r * c;
  }

  @Override
  public boolean equals(Object obj) {
    Point other = (Point) obj;
    return r == other.r && c == other.c;
  }
}
