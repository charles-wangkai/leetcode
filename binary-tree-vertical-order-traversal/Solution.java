import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
  int currentLeftColNum;

  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> result = new LinkedList<>();

    Queue<Element> queue = new LinkedList<>();
    queue.offer(new Element(root, 0, 0));
    while (!queue.isEmpty()) {
      Element head = queue.poll();

      if (head.node == null) {
        continue;
      }

      int updatedIndex = head.index + (currentLeftColNum - head.leftColNum);

      if (updatedIndex == -1) {
        result.add(0, new ArrayList<>());

        currentLeftColNum++;
        updatedIndex++;
      } else if (updatedIndex == result.size()) {
        result.add(new ArrayList<>());
      }

      result.get(updatedIndex).add(head.node.val);

      queue.offer(new Element(head.node.left, currentLeftColNum, updatedIndex - 1));
      queue.offer(new Element(head.node.right, currentLeftColNum, updatedIndex + 1));
    }

    return result;
  }
}

class Element {
  TreeNode node;
  int leftColNum;
  int index;

  Element(TreeNode node, int leftColNum, int index) {
    this.node = node;
    this.leftColNum = leftColNum;
    this.index = index;
  }
}
