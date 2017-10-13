import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Stack;

public class TopKFrequentWords {
	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> word2count = new HashMap<String, Integer>();
		for (String word : words) {
			word2count.put(word, word2count.getOrDefault(word, 0) + 1);
		}

		PriorityQueue<Element> pq = new PriorityQueue<Element>();
		for (Entry<String, Integer> entry : word2count.entrySet()) {
			String word = entry.getKey();
			int count = entry.getValue();
			Element element = new Element(word, count);
			if (pq.size() == k) {
				if (pq.peek().compareTo(element) < 0) {
					pq.poll();
					pq.offer(element);
				}
			} else {
				pq.offer(element);
			}
		}

		Stack<String> stack = new Stack<String>();
		while (!pq.isEmpty()) {
			stack.push(pq.poll().word);
		}

		List<String> result = new ArrayList<String>();
		while (!stack.empty()) {
			result.add(stack.pop());
		}
		return result;
	}
}

class Element implements Comparable<Element> {
	String word;
	int count;

	Element(String word, int count) {
		this.word = word;
		this.count = count;
	}

	@Override
	public int compareTo(Element other) {
		return (count != other.count) ? Integer.compare(count, other.count) : other.word.compareTo(word);
	}
}