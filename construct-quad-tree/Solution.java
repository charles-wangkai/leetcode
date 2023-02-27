// Definition for a QuadTree node.
class Node {
  public boolean val;
  public boolean isLeaf;
  public Node topLeft;
  public Node topRight;
  public Node bottomLeft;
  public Node bottomRight;

  public Node() {
    this.val = false;
    this.isLeaf = false;
    this.topLeft = null;
    this.topRight = null;
    this.bottomLeft = null;
    this.bottomRight = null;
  }

  public Node(boolean val, boolean isLeaf) {
    this.val = val;
    this.isLeaf = isLeaf;
    this.topLeft = null;
    this.topRight = null;
    this.bottomLeft = null;
    this.bottomRight = null;
  }

  public Node(
      boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
    this.val = val;
    this.isLeaf = isLeaf;
    this.topLeft = topLeft;
    this.topRight = topRight;
    this.bottomLeft = bottomLeft;
    this.bottomRight = bottomRight;
  }
}

class Solution {
  public Node construct(int[][] grid) {
    return buildNode(grid, 0, 0, grid.length);
  }

  Node buildNode(int[][] grid, int beginR, int beginC, int size) {
    if (size == 1) {
      return new Node(grid[beginR][beginC] == 1, true);
    }

    int nextSize = size / 2;
    Node topLeft = buildNode(grid, beginR, beginC, nextSize);
    Node topRight = buildNode(grid, beginR, beginC + nextSize, nextSize);
    Node bottomLeft = buildNode(grid, beginR + nextSize, beginC, nextSize);
    Node bottomRight = buildNode(grid, beginR + nextSize, beginC + nextSize, nextSize);

    return (topLeft.isLeaf
            && topRight.isLeaf
            && bottomLeft.isLeaf
            && bottomRight.isLeaf
            && topLeft.val == topRight.val
            && topRight.val == bottomLeft.val
            && bottomLeft.val == bottomRight.val)
        ? new Node(topLeft.val, true)
        : new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
  }
}
