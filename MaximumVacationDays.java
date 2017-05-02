import java.util.Arrays;

public class MaximumVacationDays {
	public int maxVacationDays(int[][] flights, int[][] days) {
		int N = flights.length;
		int K = days[0].length;

		int[] vacations = new int[N];
		Arrays.fill(vacations, -1);
		vacations[0] = 0;

		for (int i = 0; i < K; i++) {
			int[] nextVacations = new int[N];
			Arrays.fill(nextVacations, -1);

			for (int j = 0; j < N; j++) {
				if (vacations[j] < 0) {
					continue;
				}

				for (int k = 0; k < N; k++) {
					if (k == j || flights[j][k] == 1) {
						nextVacations[k] = Math.max(nextVacations[k], vacations[j] + days[k][i]);
					}
				}
			}

			vacations = nextVacations;
		}

		return Arrays.stream(vacations).max().getAsInt();
	}
}
