import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution {
	public List<List<Integer>> groupThePeople(int[] groupSizes) {
		Map<Integer, Stack<Integer>> sizeToIds = new HashMap<>();
		for (int i = 0; i < groupSizes.length; i++) {
			if (!sizeToIds.containsKey(groupSizes[i])) {
				sizeToIds.put(groupSizes[i], new Stack<>());
			}

			sizeToIds.get(groupSizes[i]).add(i);
		}

		List<List<Integer>> result = new ArrayList<>();
		for (int size : sizeToIds.keySet()) {
			Stack<Integer> ids = sizeToIds.get(size);
			while (!ids.empty()) {
				List<Integer> group = new ArrayList<>();
				for (int i = 0; i < size; i++) {
					group.add(ids.pop());
				}

				result.add(group);
			}
		}

		return result;
	}
}
