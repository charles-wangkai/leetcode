import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
	public String convert(String s, int nRows) {
		if (nRows == 1) {
			return s;
		}

		@SuppressWarnings("unchecked")
		List<Character> rows[] = new ArrayList[nRows];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = new ArrayList<Character>();
		}

		int rowIndex = 0;
		int offset = 1;
		for (int i = 0; i < s.length(); i++) {
			rows[rowIndex].add(s.charAt(i));
			rowIndex += offset;

			if (rowIndex == rows.length) {
				rowIndex = rows.length - 2;
				offset = -1;
			} else if (rowIndex == -1) {
				rowIndex = 1;
				offset = 1;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (List<Character> row : rows) {
			for (char ch : row) {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
}
