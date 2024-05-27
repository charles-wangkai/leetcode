import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<Boolean> getResults(int[][] queries) {
    List<Boolean> result = new ArrayList<>();
    Node segmentTree =
        buildNode(0, Arrays.stream(queries).mapToInt(query -> query[1]).max().getAsInt());
    for (int[] query : queries) {
      if (query[0] == 1) {
        update(query[1], segmentTree);
      } else {
        result.add(findMaxSize(segmentTree, query[1]) >= query[2]);
      }
    }

    return result;
  }

  int findMaxSize(Node segmentTree, int x) {
    List<Node> contained = new ArrayList<>();
    search(contained, x, segmentTree);

    int result = -1;
    int lastObstacle = 0;
    for (Node node : contained) {
      result = Math.max(result, node.maxSize);

      if (node.minObstacle != -1) {
        result = Math.max(result, node.minObstacle - lastObstacle);
        lastObstacle = node.maxObstacle;
      }
    }
    result = Math.max(result, x - lastObstacle);

    return result;
  }

  void search(List<Node> contained, int x, Node node) {
    if (node.end <= x) {
      contained.add(node);
    } else if (node.begin <= x) {
      search(contained, x, node.left);
      search(contained, x, node.right);
    }
  }

  void update(int x, Node node) {
    if (node.begin <= x && node.end >= x) {
      if (node.begin == node.end) {
        node.minObstacle = x;
        node.maxObstacle = x;
      } else {
        update(x, node.left);
        update(x, node.right);

        if (node.left.minObstacle == -1) {
          node.maxSize =
              Math.max(
                  Math.max(node.left.maxSize, node.right.maxSize),
                  node.right.minObstacle - node.left.begin);
          node.minObstacle = node.right.minObstacle;
          node.maxObstacle = node.right.maxObstacle;
        } else if (node.right.minObstacle == -1) {
          node.maxSize =
              Math.max(
                  Math.max(node.left.maxSize, node.right.maxSize),
                  node.right.end - node.left.maxObstacle);
          node.minObstacle = node.left.minObstacle;
          node.maxObstacle = node.left.maxObstacle;
        } else {
          node.maxSize =
              Math.max(
                  Math.max(node.left.maxSize, node.right.maxSize),
                  node.right.minObstacle - node.left.maxObstacle);
          node.minObstacle = node.left.minObstacle;
          node.maxObstacle = node.right.maxObstacle;
        }
      }
    }
  }

  Node buildNode(int begin, int end) {
    if (begin == end) {
      return new Node(begin, end, 0, -1, -1, null, null);
    }

    int middle = (begin + end) / 2;

    return new Node(
        begin, end, end - begin, -1, -1, buildNode(begin, middle), buildNode(middle + 1, end));
  }
}

class Node {
  int begin;
  int end;
  int maxSize;
  int minObstacle;
  int maxObstacle;
  Node left;
  Node right;

  Node(int begin, int end, int maxSize, int minObstacle, int maxObstacle, Node left, Node right) {
    this.begin = begin;
    this.end = end;
    this.maxSize = maxSize;
    this.minObstacle = minObstacle;
    this.maxObstacle = maxObstacle;
    this.left = left;
    this.right = right;
  }
}