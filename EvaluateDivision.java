import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		List<String> variables = new ArrayList<String>();
		Map<String, Integer> variable2index = new HashMap<String, Integer>();
		for (String[] equation : equations) {
			for (String variable : equation) {
				if (!variable2index.containsKey(variable)) {
					variables.add(variable);
					variable2index.put(variable, variables.size() - 1);
				}
			}
		}

		int n = variables.size();
		Double[][] matrix = new Double[n][n];

		for (int i = 0; i < equations.length; i++) {
			String numerator = equations[i][0];
			String denominator = equations[i][1];

			matrix[variable2index.get(numerator)][variable2index.get(denominator)] = values[i];
			matrix[variable2index.get(denominator)][variable2index.get(numerator)] = 1 / values[i];
		}

		computeClosure(matrix);

		double[] results = new double[queries.length];
		for (int i = 0; i < results.length; i++) {
			String numerator = queries[i][0];
			String denominator = queries[i][1];

			Double result = null;
			if (variable2index.containsKey(numerator) && variable2index.containsKey(denominator)) {
				result = matrix[variable2index.get(numerator)][variable2index.get(denominator)];
			}

			results[i] = (result == null) ? -1 : result;
		}
		return results;
	}

	void computeClosure(Double[][] matrix) {
		int n = matrix.length;

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (matrix[i][k] != null && matrix[k][j] != null) {
						matrix[i][j] = matrix[i][k] * matrix[k][j];
					}
				}
			}
		}
	}
}
