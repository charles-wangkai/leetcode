import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int calPoints(String[] ops) {
		List<Integer> points = new ArrayList<Integer>();
		for (String op : ops) {
			if (op.equals("+")) {
				points.add(points.get(points.size() - 2) + points.get(points.size() - 1));
			} else if (op.equals("D")) {
				points.add(points.get(points.size() - 1) * 2);
			} else if (op.equals("C")) {
				points.remove(points.size() - 1);
			} else {
				points.add(Integer.parseInt(op));
			}
		}
		return points.stream().mapToInt(x -> x).sum();
	}
}
