import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> history = new HashSet<Integer>();
		for (int number : nums) {
			if (history.contains(number)) {
				return true;
			}
			history.add(number);
		}
		return false;
	}
}
