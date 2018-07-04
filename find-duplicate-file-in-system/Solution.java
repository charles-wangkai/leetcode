import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	public List<List<String>> findDuplicate(String[] paths) {
		Map<String, List<String>> content2paths = new HashMap<String, List<String>>();
		for (String path : paths) {
			String[] fields = path.split(" ");
			String directory = fields[0];
			for (int i = 1; i < fields.length; i++) {
				int startIndex = fields[i].indexOf('(');
				String fileName = fields[i].substring(0, startIndex);
				String content = fields[i].substring(startIndex + 1, fields[i].length() - 1);

				if (!content2paths.containsKey(content)) {
					content2paths.put(content, new ArrayList<String>());
				}
				content2paths.get(content).add(directory + "/" + fileName);
			}
		}

		return content2paths.values().stream().filter(ps -> ps.size() >= 2).collect(Collectors.toList());
	}
}
