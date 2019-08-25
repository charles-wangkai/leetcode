import java.util.HashMap;
import java.util.Map;

public class FileSystem {
	private Map<String, Integer> pathToValue = new HashMap<>();

	public boolean create(String path, int value) {
		String parentPath = path.substring(0, path.lastIndexOf('/'));
		if (pathToValue.containsKey(path) || (!parentPath.isEmpty() && !pathToValue.containsKey(parentPath))) {
			return false;
		}

		pathToValue.put(path, value);

		return true;
	}

	public int get(String path) {
		return pathToValue.getOrDefault(path, -1);
	}
}

// Your FileSystem object will be instantiated and called as such:
// FileSystem obj = new FileSystem();
// boolean param_1 = obj.create(path,value);
// int param_2 = obj.get(path);
