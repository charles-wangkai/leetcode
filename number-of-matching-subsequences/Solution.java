import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public int numMatchingSubseq(String S, String[] words) {
		Map<Character, List<Element>> letter2elements = new HashMap<Character, List<Element>>();
		for (String word : words) {
			char letter = word.charAt(0);
			addElement(letter2elements, letter, new Element(word, 0));
		}

		int result = 0;
		for (int i = 0; i < S.length(); i++) {
			char letter = S.charAt(i);
			if (letter2elements.containsKey(letter)) {
				List<Element> elements = letter2elements.get(letter);

				letter2elements.put(letter, new ArrayList<Element>());

				for (Element element : elements) {
					element.index++;
					if (element.index == element.word.length()) {
						result++;
					} else {
						addElement(letter2elements, element.word.charAt(element.index), element);
					}
				}
			}
		}
		return result;
	}

	void addElement(Map<Character, List<Element>> letter2elements, char letter, Element element) {
		if (!letter2elements.containsKey(letter)) {
			letter2elements.put(letter, new ArrayList<Element>());
		}
		letter2elements.get(letter).add(element);
	}
}

class Element {
	String word;
	int index;

	Element(String word, int index) {
		this.word = word;
		this.index = index;
	}
}