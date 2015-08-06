import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Flatten2DVector {
	private Iterator<List<Integer>> rowIter;
	private Iterator<Integer> colIter;

	public Flatten2DVector(List<List<Integer>> vec2d) {
		rowIter = vec2d.iterator();
		if (rowIter.hasNext()) {
			colIter = rowIter.next().iterator();
			skipLineGaps();
		} else {
			colIter = Collections.emptyIterator();
		}
	}

	public int next() {
		int result = colIter.next();
		skipLineGaps();
		return result;
	}

	public boolean hasNext() {
		return colIter.hasNext();
	}

	private void skipLineGaps() {
		while (!colIter.hasNext() && rowIter.hasNext()) {
			colIter = rowIter.next().iterator();
		}
	}
}

// Your Vector2D object will be instantiated and called as such:
// Vector2D i = new Vector2D(vec2d);
// while (i.hasNext()) v[f()] = i.next();
