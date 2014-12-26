import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TwoSum_III_DataStructureDesign {
	Map<Integer, Boolean> number2duplicated = new HashMap<Integer, Boolean>();

	public void add(int number) {
		if (!number2duplicated.containsKey(number)) {
			number2duplicated.put(number, false);
		} else if (!number2duplicated.get(number)) {
			number2duplicated.put(number, true);
		}
	}

	public boolean find(int value) {
		for (Entry<Integer, Boolean> entry : number2duplicated.entrySet()) {
			int number = entry.getKey();
			boolean duplicated = entry.getValue();
			int other = value - number;
			if ((other == number && duplicated)
					|| (other != number && number2duplicated.containsKey(other))) {
				return true;
			}
		}
		return false;
	}
}
