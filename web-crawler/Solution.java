import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// This is the HtmlParser's API interface.
// You should not implement it, or speculate about its implementation
interface HtmlParser {
	public List<String> getUrls(String url);
}

public class Solution {
	public List<String> crawl(String startUrl, HtmlParser htmlParser) {
		String targetHostname = extractHostname(startUrl);

		Set<String> result = new HashSet<>();
		result.add(startUrl);

		Queue<String> queue = new LinkedList<>();
		queue.offer(startUrl);

		while (!queue.isEmpty()) {
			String head = queue.poll();

			for (String url : htmlParser.getUrls(head)) {
				String hostname = extractHostname(url);

				if (!result.contains(url) && hostname.equals(targetHostname)) {
					result.add(url);
					queue.offer(url);
				}
			}
		}

		return new ArrayList<>(result);
	}

	String extractHostname(String url) {
		int endIndex = url.indexOf('/', "http://".length());
		if (endIndex == -1) {
			endIndex = url.length();
		}

		return url.substring(0, endIndex).toLowerCase();
	}
}
