import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

// This is the HtmlParser's API interface.
// You should not implement it, or speculate about its implementation
interface HtmlParser {
	public List<String> getUrls(String url);
}

public class Solution {
	static final int BATCH_MAX_SIZE = 10;

	public List<String> crawl(String startUrl, HtmlParser htmlParser) {
		String targetHostname = extractHostname(startUrl);

		Set<String> result = Collections.synchronizedSet(new HashSet<>());
		result.add(startUrl);

		Queue<String> queue = new ConcurrentLinkedQueue<>();
		queue.offer(startUrl);

		while (!queue.isEmpty()) {
			List<String> batch = new ArrayList<>();
			while (!queue.isEmpty() && batch.size() < BATCH_MAX_SIZE) {
				batch.add(queue.poll());
			}

			batch.parallelStream().forEach(current -> {
				for (String url : htmlParser.getUrls(current)) {
					String hostname = extractHostname(url);

					if (!result.contains(url) && hostname.equals(targetHostname)) {
						result.add(url);
						queue.offer(url);
					}
				}
			});
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
