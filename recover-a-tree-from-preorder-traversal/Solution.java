import java.util.ArrayList;
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
	List<Element> elements;
	int elementIndex;

	public TreeNode recoverFromPreorder(String S) {
		buildElements(S);
		elementIndex = 0;

		return buildNode(0);
	}

	void buildElements(String S) {
		elements = new ArrayList<>();
		int depth = 0;
		int val = 0;
		for (int i = 0; i <= S.length(); i++) {
			if (i != S.length() && S.charAt(i) != '-') {
				val = val * 10 + (S.charAt(i) - '0');
			} else if (i >= 1 && S.charAt(i - 1) == '-') {
				depth++;
			} else {
				elements.add(new Element(depth, val));

				depth = 1;
				val = 0;
			}
		}
	}

	TreeNode buildNode(int depth) {
		if (elementIndex == elements.size()) {
			return null;
		}

		Element nextElement = elements.get(elementIndex);
		if (depth != nextElement.depth) {
			return null;
		}

		elementIndex++;

		TreeNode node = new TreeNode(nextElement.val);
		node.left = buildNode(depth + 1);
		node.right = buildNode(depth + 1);
		return node;
	}
}

class Element {
	int depth;
	int val;

	Element(int depth, int val) {
		this.depth = depth;
		this.val = val;
	}
}