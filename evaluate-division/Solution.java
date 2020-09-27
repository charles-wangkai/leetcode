import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		List<String> variables = new ArrayList<>();
		Map<String, Integer> variableToIndex = new HashMap<>();
		for (List<String> equation : equations) {
			for (String variable : equation) {
				if (!variableToIndex.containsKey(variable)) {
					variables.add(variable);
					variableToIndex.put(variable, variables.size() - 1);
				}
			}
		}

		int n = variables.size();
		Double[][] matrix = new Double[n][n];

		for (int i = 0; i < equations.size(); ++i) {
			String numerator = equations.get(i).get(0);
			String denominator = equations.get(i).get(1);

			matrix[variableToIndex.get(numerator)][variableToIndex.get(denominator)] = values[i];
			matrix[variableToIndex.get(denominator)][variableToIndex.get(numerator)] = 1 / values[i];
		}

		computeClosure(matrix);

		double[] results = new double[queries.size()];
		for (int i = 0; i < results.length; ++i) {
			String numerator = queries.get(i).get(0);
			String denominator = queries.get(i).get(1);

			Double result = (variableToIndex.containsKey(numerator) && variableToIndex.containsKey(denominator))
					? matrix[variableToIndex.get(numerator)][variableToIndex.get(denominator)]
					: null;

			results[i] = (result == null) ? -1 : result;
		}
		return results;
	}

	void computeClosure(Double[][] matrix) {
		int n = matrix.length;

		for (int k = 0; k < n; ++k) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (matrix[i][k] != null && matrix[k][j] != null) {
						matrix[i][j] = matrix[i][k] * matrix[k][j];
					}
				}
			}
		}
	}
}
