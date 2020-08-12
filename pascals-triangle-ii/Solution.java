import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
	public List<Integer> getRow(int rowIndex) {
		List<Integer> row = Collections.singletonList(1);

		for (int i = 1; i <= rowIndex; ++i) {
			List<Integer> nextRow = new ArrayList<>();
			nextRow.add(1);
			for (int j = 0; j < row.size() - 1; ++j) {
				nextRow.add(row.get(j) + row.get(j + 1));
			}
			nextRow.add(1);

			row = nextRow;
		}

		return row;
	}
}
