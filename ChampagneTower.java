import java.util.Arrays;

public class ChampagneTower {
	public double champagneTower(int poured, int query_row, int query_glass) {
		double[] row = { poured };
		while (row.length < query_row + 1) {
			double[] excesses = Arrays.stream(row).map(p -> Math.max(0, p - 1)).toArray();

			row = new double[excesses.length + 1];
			for (int i = 0; i < excesses.length; i++) {
				row[i] += excesses[i] / 2;
				row[i + 1] += excesses[i] / 2;
			}
		}
		return Math.min(1, row[query_glass]);
	}
}
