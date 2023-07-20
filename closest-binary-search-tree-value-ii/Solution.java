import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class Solution {
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    Deque<DirectionAndNode> leftPath = new ArrayDeque<>();
    Deque<DirectionAndNode> rightPath = new ArrayDeque<>();

    Deque<DirectionAndNode> path = new ArrayDeque<>();
    boolean leftOrRight = false;
    TreeNode node = root;
    while (node != null) {
      path.push(new DirectionAndNode(leftOrRight, node));

      if (node.val >= target) {
        rightPath = new ArrayDeque<>(path);
        node = node.left;
        leftOrRight = true;
      } else {
        leftPath = new ArrayDeque<>(path);
        node = node.right;
        leftOrRight = false;
      }
    }

    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      Integer leftValue = getValue(leftPath);
      Integer rightValue = getValue(rightPath);
      if (rightValue == null
          || (leftValue != null && Math.abs(leftValue - target) < Math.abs(rightValue - target))) {
        result.add(leftValue);
        moveToPredecessor(leftPath);
      } else {
        result.add(rightValue);
        moveToSuccessor(rightPath);
      }
    }
    return result;
  }

  Integer getValue(Deque<DirectionAndNode> path) {
    return path.isEmpty() ? null : path.peek().node.val;
  }

  void moveToPredecessor(Deque<DirectionAndNode> path) {
    if (path.isEmpty()) {
      return;
    }

    if (path.peek().node.left != null) {
      path.push(new DirectionAndNode(true, path.peek().node.left));
      while (path.peek().node.right != null) {
        path.push(new DirectionAndNode(false, path.peek().node.right));
      }
    } else {
      while (!path.isEmpty() && path.peek().leftOrRight) {
        path.pop();
      }
      if (!path.isEmpty()) {
        path.pop();
      }
    }
  }

  void moveToSuccessor(Deque<DirectionAndNode> path) {
    if (path.isEmpty()) {
      return;
    }

    if (path.peek().node.right != null) {
      path.push(new DirectionAndNode(false, path.peek().node.right));
      while (path.peek().node.left != null) {
        path.push(new DirectionAndNode(true, path.peek().node.left));
      }
    } else {
      while (!path.isEmpty() && !path.peek().leftOrRight) {
        path.pop();
      }
      if (!path.isEmpty()) {
        path.pop();
      }
    }
  }
}

class DirectionAndNode {
  boolean leftOrRight;
  TreeNode node;

  DirectionAndNode(boolean leftOrRight, TreeNode node) {
    this.leftOrRight = leftOrRight;
    this.node = node;
  }
}
