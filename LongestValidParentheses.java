import java.util.Stack;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> leftIndices = new Stack<Integer>();
        leftIndices.add(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftIndices.add(i);
            } else {
                leftIndices.pop();
                if (leftIndices.empty()) {
                    leftIndices.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - leftIndices.peek());
                }
            }
        }
        return maxLength;
    }
}
