import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// This is the interface for the expression tree Node.
// You should not remove it, and you can define some classes to implement it.

abstract class Node {
  static final Map<String, Operator> SYMBOL_TO_OPERATOR = buildSymbolToOperator();

  static Map<String, Operator> buildSymbolToOperator() {
    Map<String, Operator> result = new HashMap<>();
    result.put("+", (x, y) -> x + y);
    result.put("-", (x, y) -> x - y);
    result.put("*", (x, y) -> x * y);
    result.put("/", (x, y) -> x / y);

    return result;
  }

  public abstract int evaluate();

  // define your fields here
  String value;
  Node left;
  Node right;

  boolean isOperator() {
    return SYMBOL_TO_OPERATOR.containsKey(value);
  }
}

interface Operator {
  int operate(int x, int y);
}

class TreeNode extends Node {
  @Override
  public int evaluate() {
    if (isOperator()) {
      return SYMBOL_TO_OPERATOR.get(value).operate(left.evaluate(), right.evaluate());
    }

    return Integer.valueOf(value);
  }
}

// This is the TreeBuilder class.
// You can treat it as the driver code that takes the postinfix input
// and returns the expression tree represnting it as a Node.

class TreeBuilder {
  Node buildTree(String[] postfix) {
    Deque<Node> stack = new ArrayDeque<>();
    for (String s : postfix) {
      Node node = new TreeNode();
      node.value = s;
      if (node.isOperator()) {
        node.right = stack.pop();
        node.left = stack.pop();
      }
      stack.push(node);
    }

    return stack.peek();
  }
}

// Your TreeBuilder object will be instantiated and called as such:
// TreeBuilder obj = new TreeBuilder();
// Node expTree = obj.buildTree(postfix);
// int ans = expTree.evaluate();
