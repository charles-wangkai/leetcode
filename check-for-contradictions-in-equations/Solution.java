import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  static final double EPSILON = 1e-5;

  public boolean checkContradictions(List<List<String>> equations, double[] values) {
    Map<String, List<Integer>> variableToEdges = new HashMap<>();
    for (int i = 0; i < equations.size(); ++i) {
      for (String variable : equations.get(i)) {
        variableToEdges.putIfAbsent(variable, new ArrayList<>());
        variableToEdges.get(variable).add(i);
      }
    }

    Map<String, Double> variableToValue = new HashMap<>();
    for (String variable : variableToEdges.keySet()) {
      if (!variableToValue.containsKey(variable)) {
        if (!search(equations, values, variableToEdges, variableToValue, variable, 1)) {
          return true;
        }
      }
    }

    return false;
  }

  boolean search(
      List<List<String>> equations,
      double[] values,
      Map<String, List<Integer>> variableToEdges,
      Map<String, Double> variableToValue,
      String variable,
      double value) {
    if (variableToValue.containsKey(variable)) {
      return isEqual(variableToValue.get(variable), value);
    }
    variableToValue.put(variable, value);

    for (int edge : variableToEdges.get(variable)) {
      String nextVariable;
      double nextValue;
      if (equations.get(edge).get(0).equals(variable)) {
        nextVariable = equations.get(edge).get(1);
        nextValue = value / values[edge];
      } else {
        nextVariable = equations.get(edge).get(0);
        nextValue = value * values[edge];
      }

      if (!search(equations, values, variableToEdges, variableToValue, nextVariable, nextValue)) {
        return false;
      }
    }

    return true;
  }

  boolean isEqual(double x, double y) {
    return Math.abs(x - y) / Math.min(Math.abs(x), Math.abs(y)) < EPSILON;
  }
}
