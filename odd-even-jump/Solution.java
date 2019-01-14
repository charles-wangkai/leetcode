import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class Solution {
	public int oddEvenJumps(int[] A) {
		boolean[] oddGoods = new boolean[A.length];
		boolean[] evenGoods = new boolean[A.length];
		NavigableMap<Integer, Integer> valueToFirstIndex = new TreeMap<>();
		for (int i = A.length - 1; i >= 0; i--) {
			if (i == A.length - 1) {
				oddGoods[i] = true;
			} else {
				Integer value = valueToFirstIndex.ceilingKey(A[i]);
				if (value == null) {
					oddGoods[i] = false;
				} else {
					oddGoods[i] = evenGoods[valueToFirstIndex.get(value)];
				}
			}

			if (i == A.length - 1) {
				evenGoods[i] = true;
			} else {
				Integer value = valueToFirstIndex.floorKey(A[i]);
				if (value == null) {
					evenGoods[i] = false;
				} else {
					evenGoods[i] = oddGoods[valueToFirstIndex.get(value)];
				}
			}

			valueToFirstIndex.put(A[i], i);
		}
		return (int) IntStream.range(0, oddGoods.length).filter(i -> oddGoods[i]).count();
	}
}
