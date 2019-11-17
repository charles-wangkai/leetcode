import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
		Map<String, String> regionToParent = new HashMap<>();
		for (List<String> level : regions) {
			String parent = level.get(0);
			for (int i = 1; i < level.size(); i++) {
				regionToParent.put(level.get(i), parent);
			}
		}

		List<String> path1 = findPath(regionToParent, region1);
		List<String> path2 = findPath(regionToParent, region2);

		int index = 0;
		while (index + 1 < path1.size() && index + 1 < path2.size()
				&& path1.get(index + 1).equals(path2.get(index + 1))) {
			index++;
		}

		return path1.get(index);
	}

	List<String> findPath(Map<String, String> regionToParent, String region) {
		List<String> result = new ArrayList<>();
		while (true) {
			result.add(region);

			if (!regionToParent.containsKey(region)) {
				break;
			}

			region = regionToParent.get(region);
		}

		Collections.reverse(result);

		return result;
	}
}
