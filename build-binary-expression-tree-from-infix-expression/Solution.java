// Definition for a binary tree node.
class Node {
    char val;
    Node left;
    Node right;

    Node() {
        this.val = ' ';
    }

    Node(char val) {
        this.val = val;
    }

    Node(char val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public Node expTree(String s) {
        return buildExpTree(s, 0, s.length() - 1);
    }

    Node buildExpTree(String s, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            return new Node(s.charAt(beginIndex));
        }

        int depth = 0;
        for (int i = endIndex; i >= beginIndex; --i) {
            char ch = s.charAt(i);
            if (ch == ')') {
                ++depth;
            } else if (ch == '(') {
                --depth;
            } else if (depth == 0 && (ch == '+' || ch == '-')) {
                return new Node(ch, buildExpTree(s, beginIndex, i - 1), buildExpTree(s, i + 1, endIndex));
            }
        }

        depth = 0;
        for (int i = endIndex; i >= beginIndex; --i) {
            char ch = s.charAt(i);
            if (ch == ')') {
                ++depth;
            } else if (ch == '(') {
                --depth;
            } else if (depth == 0 && (ch == '*' || ch == '/')) {
                return new Node(ch, buildExpTree(s, beginIndex, i - 1), buildExpTree(s, i + 1, endIndex));
            }
        }

        return buildExpTree(s, beginIndex + 1, endIndex - 1);
    }
}