import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean equationsPossible(String[] equations) {
    Map<Character, Character> variableToParent = new HashMap<>();
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

    return Arrays.stream(equations)
        .filter(equation -> equation.charAt(1) == '!')
        .allMatch(
            equation -> {
              char leftVariable = equation.charAt(0);
              char rightVariable = equation.charAt(3);

              char leftRoot = findRoot(variableToParent, leftVariable);
              char rightRoot = findRoot(variableToParent, rightVariable);

              return leftRoot != rightRoot;
            });
  }

  char findRoot(Map<Character, Character> variableToParent, char variable) {
    char root = variable;
    while (variableToParent.containsKey(root)) {
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
