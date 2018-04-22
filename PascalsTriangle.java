import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> rows = new ArrayList<List<Integer>>();
		for (int i = 0; i < numRows; i++) {
			List<Integer> row = new ArrayList<Integer>();
			row.add(1);
			if (!rows.isEmpty()) {
				List<Integer> lastRow = rows.get(rows.size() - 1);
				for (int j = 0; j < lastRow.size() - 1; j++) {
					row.add(lastRow.get(j) + lastRow.get(j + 1));
				}
				row.add(1);
			}
			rows.add(row);
		}
		return rows;
	}
}
