import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<Boolean> getResults(int[][] queries) {
    SegTree segTree =
        new SegTree(Arrays.stream(queries).mapToInt(query -> query[1]).max().getAsInt());

    List<Boolean> result = new ArrayList<>();
    for (int[] query : queries) {
      if (query[0] == 1) {
        segTree.update(query[1]);
      } else {
        result.add(segTree.query(query[1]) >= query[2]);
      }
    }

    return result;
  }
}

class SegTree {
  Node root;

  SegTree(int maxIndex) {
    root = buildNode(0, maxIndex);
  }

  private Node buildNode(int beginIndex, int endIndex) {
    Node node = new Node(beginIndex, endIndex);

    if (beginIndex == endIndex) {
      node.maxSize = 0;
      node.minObstacle = -1;
      node.maxObstacle = -1;
    } else {
      int middleIndex = (beginIndex + endIndex) / 2;
      node.left = buildNode(beginIndex, middleIndex);
      node.right = buildNode(middleIndex + 1, endIndex);

      node.pull();
    }

    return node;
  }

  void update(int x) {
    update(x, root);
  }

  private void update(int x, Node node) {
    if (node.beginIndex <= x && node.endIndex >= x) {
      if (node.beginIndex == node.endIndex) {
        node.minObstacle = x;
        node.maxObstacle = x;
      } else {
        update(x, node.left);
        update(x, node.right);

        node.pull();
      }
    }
  }

  int query(int x) {
    List<Node> contained = new ArrayList<>();
    search(contained, x, root);

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

  private void search(List<Node> contained, int x, Node node) {
    if (node.endIndex <= x) {
      contained.add(node);
    } else if (node.beginIndex <= x) {
      search(contained, x, node.left);
      search(contained, x, node.right);
    }
  }

  static class Node {
    int beginIndex;
    int endIndex;
    int maxSize;
    int minObstacle;
    int maxObstacle;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
    }

    void pull() {
      if (left.minObstacle == -1) {
        maxSize =
            Math.max(
                Math.max(left.maxSize, right.maxSize),
                (right.minObstacle == -1)
                    ? Integer.MIN_VALUE
                    : (right.minObstacle - left.beginIndex));
        minObstacle = right.minObstacle;
        maxObstacle = right.maxObstacle;
      } else if (right.minObstacle == -1) {
        maxSize =
            Math.max(Math.max(left.maxSize, right.maxSize), right.endIndex - left.maxObstacle);
        minObstacle = left.minObstacle;
        maxObstacle = left.maxObstacle;
      } else {
        maxSize =
            Math.max(Math.max(left.maxSize, right.maxSize), right.minObstacle - left.maxObstacle);
        minObstacle = left.minObstacle;
        maxObstacle = right.maxObstacle;
      }
    }
  }
}
