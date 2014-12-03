import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle_II {
	public List<Integer> getRow(int rowIndex) {
		List<Integer> row = new ArrayList<Integer>();
		row.add(1);
		for (int i = 1; i <= rowIndex; i++) {
			List<Integer> nextRow = new ArrayList<Integer>();
			nextRow.add(1);
			for (int j = 0; j < row.size() - 1; j++) {
				nextRow.add(row.get(j) + row.get(j + 1));
			}
			nextRow.add(1);
			row = nextRow;
		}
		return row;
	}
}
