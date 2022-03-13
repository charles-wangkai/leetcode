import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
    Set<Point> digs =
        Arrays.stream(dig).map(d -> new Point(d[0], d[1])).collect(Collectors.toSet());

    return (int)
        Arrays.stream(artifacts)
            .filter(
                artifact ->
                    IntStream.rangeClosed(artifact[0], artifact[2])
                        .allMatch(
                            r ->
                                IntStream.rangeClosed(artifact[1], artifact[3])
                                    .allMatch(c -> digs.contains(new Point(r, c)))))
            .count();
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
    return Objects.hash(r, c);
  }

  @Override
  public boolean equals(Object obj) {
    Point other = (Point) obj;

    return r == other.r && c == other.c;
  }
}