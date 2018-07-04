import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
	public List<Integer> selfDividingNumbers(int left, int right) {
		return IntStream.rangeClosed(left, right)
				.filter(n -> String.valueOf(n).chars().allMatch(digit -> digit != '0' && n % (digit - '0') == 0))
				.collect(ArrayList<Integer>::new, List::add, List::addAll);
	}
}
