import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public double[] calcEquation(
      List<List<String>> equations, double[] values, List<List<String>> queries) {
    Map<String, Integer> variableToIndex = new HashMap<>();
    for (List<String> equation : equations) {
      for (String variable : equation) {
        variableToIndex.putIfAbsent(variable, variableToIndex.size());
      }
    }

    Double[][] matrix = new Double[variableToIndex.size()][variableToIndex.size()];
    for (int i = 0; i < equations.size(); ++i) {
      int numerIndex = variableToIndex.get(equations.get(i).get(0));
      int denomIndex = variableToIndex.get(equations.get(i).get(1));

      matrix[numerIndex][denomIndex] = values[i];
      matrix[denomIndex][numerIndex] = 1 / values[i];
    }
    for (int k = 0; k < variableToIndex.size(); ++k) {
      for (int i = 0; i < variableToIndex.size(); ++i) {
        for (int j = 0; j < variableToIndex.size(); ++j) {
          if (matrix[i][k] != null && matrix[k][j] != null) {
            matrix[i][j] = matrix[i][k] * matrix[k][j];
          }
        }
      }
    }

    return queries.stream()
        .mapToDouble(
            query -> {
              String numer = query.get(0);
              String denom = query.get(1);

              Double result =
                  (variableToIndex.containsKey(numer) && variableToIndex.containsKey(denom))
                      ? matrix[variableToIndex.get(numer)][variableToIndex.get(denom)]
                      : null;

              return (result == null) ? -1 : result;
            })
        .toArray();
  }
}
