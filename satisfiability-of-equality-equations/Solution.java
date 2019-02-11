import java.util.HashMap;
import java.util.Map;

public class Solution {
	public boolean equationsPossible(String[] equations) {
		Map<Character, Character> variableToParent = new HashMap<>();
		for (char variable = 'a'; variable <= 'z'; variable++) {
			variableToParent.put(variable, null);
		}

		for (String equation : equations) {
			if (equation.charAt(1) == '=') {
				char leftVariable = equation.charAt(0);
				char rightVariable = equation.charAt(3);

				char leftRoot = findRoot(variableToParent, leftVariable);
				char rightRoot = findRoot(variableToParent, rightVariable);

				if (leftRoot != rightRoot) {
					variableToParent.put(rightRoot, leftRoot);
				}
			}
		}

		for (String equation : equations) {
			if (equation.charAt(1) == '!') {
				char leftVariable = equation.charAt(0);
				char rightVariable = equation.charAt(3);

				char leftRoot = findRoot(variableToParent, leftVariable);
				char rightRoot = findRoot(variableToParent, rightVariable);

				if (leftRoot == rightRoot) {
					return false;
				}
			}
		}
		return true;
	}

	char findRoot(Map<Character, Character> variableToParent, char variable) {
		char root = variable;
		while (variableToParent.get(root) != null) {
			root = variableToParent.get(root);
		}

		char p = variable;
		while (p != root) {
			char next = variableToParent.get(p);
			variableToParent.put(p, root);

			p = next;
		}

		return root;
	}
}
