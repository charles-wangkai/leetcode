import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	final int STARTING_STEP = 1;

	public int ladderLength(String start, String end, Set<String> dict) {
		Map<String, Word> wordMap = new HashMap<String, Word>();
		for (String value : dict) {
			wordMap.put(value, new Word(value));
		}
		wordMap.put(end, new Word(end));
		Word startWord = new Word(start);
		startWord.step = STARTING_STEP;
		wordMap.put(start, startWord);

		Queue<Element> queue = new LinkedList<Element>();
		queue.offer(new Element(start, STARTING_STEP));
		boolean found = false;
		while (!queue.isEmpty()) {
			Element head = queue.poll();
			Word word = wordMap.get(head.value);
			if (word.visited) {
				continue;
			}
			word.visited = true;
			if (head.value.equals(end)) {
				found = true;
				break;
			}
			for (String candidate : findCandidates(head.value)) {
				if (!wordMap.containsKey(candidate)) {
					continue;
				}
				Word candidateWord = wordMap.get(candidate);
				if (candidateWord.step != 0
						&& candidateWord.step != head.step + 1) {
					continue;
				}
				candidateWord.step = head.step + 1;
				candidateWord.prevWords.add(head.value);
				queue.offer(new Element(candidate, head.step + 1));
			}
		}

		return found ? wordMap.get(end).step : 0;
	}

	List<String> findCandidates(String value) {
		List<String> candidates = new ArrayList<String>();
		StringBuilder sb = new StringBuilder(value);
		for (int i = 0; i < sb.length(); i++) {
			char ch = sb.charAt(i);
			for (char letter = 'a'; letter <= 'z'; letter++) {
				if (letter == ch) {
					continue;
				}
				sb.setCharAt(i, letter);
				candidates.add(sb.toString());
				sb.setCharAt(i, ch);
			}
		}
		return candidates;
	}

	void search(List<List<String>> ladders, Map<String, Word> wordMap,
			String current, LinkedList<String> ladder) {
		ladder.addFirst(current);
		Word word = wordMap.get(current);
		if (word.step == 1) {
			ladders.add(new ArrayList<String>(ladder));
		} else {
			for (String prevWord : word.prevWords) {
				search(ladders, wordMap, prevWord, ladder);
			}
		}
		ladder.removeFirst();
	}
}

class Word {
	String value;
	int step;
	boolean visited;
	Set<String> prevWords = new HashSet<String>();

	public Word(String value) {
		this.value = value;
	}
}

class Element {
	String value;
	int step;

	public Element(String value, int step) {
		this.value = value;
		this.step = step;
	}
}