import java.util.Stack;

public class SimplifyPath {
	public String simplifyPath(String path) {
		path += "/";
		Stack<String> directories = new Stack<String>();
		int prevSlashIndex = -1;
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '/') {
				String directory = path.substring(prevSlashIndex + 1, i);
				if (directory.equals("..")) {
					if (!directories.isEmpty()) {
						directories.pop();
					}
				} else if (!directory.equals(".") && !directory.equals("")) {
					directories.push(directory);
				}
				prevSlashIndex = i;
			}
		}

		if (directories.empty()) {
			return "/";
		}
		StringBuilder simplified = new StringBuilder();
		for (String directory : directories) {
			simplified.append("/").append(directory);
		}
		return simplified.toString();
	}
}
