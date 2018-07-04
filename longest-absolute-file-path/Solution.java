import java.util.Stack;

public class Solution {
	private String input;
	private int index;
	private Stack<String> path;
	int level;

	public int lengthLongestPath(String input) {
		this.input = input;
		index = 0;
		path = new Stack<String>();
		level = 0;

		int maxLength = 0;
		while (true) {
			String token = nextToken();
			if (token == null) {
				break;
			} else if (token.equals("\n")) {
				level = 0;
			} else if (token.equals("\t")) {
				level++;
			} else {
				while (path.size() > level) {
					path.pop();
				}
				path.push(token);

				if (isFile(token)) {
					maxLength = Math.max(maxLength, getPathLength());
				}
			}
		}
		return maxLength;
	}

	private String nextToken() {
		if (index == input.length()) {
			return null;
		}

		int endIndex = index + 1;
		if (input.charAt(index) != '\n' && input.charAt(index) != '\t') {
			while (endIndex < input.length() && input.charAt(endIndex) != '\n' && input.charAt(endIndex) != '\t') {
				endIndex++;
			}
		}

		String token = input.substring(index, endIndex);
		index = endIndex;
		return token;
	}

	private boolean isFile(String token) {
		return token.indexOf('.') >= 0;
	}

	private int getPathLength() {
		int pathLength = -1;
		for (String part : path) {
			pathLength += 1 + part.length();
		}
		return pathLength;
	}
}
