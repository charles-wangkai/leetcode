import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReorganizeString {
	public String reorganizeString(String S) {
		Map<Character, Integer> ch2count = new HashMap<Character, Integer>();
		for (char ch : S.toCharArray()) {
			ch2count.put(ch, ch2count.getOrDefault(ch, 0) + 1);
		}

		List<Element> elements = ch2count.entrySet().stream()
				.map(entry -> new Element(entry.getKey(), entry.getValue())).collect(Collectors.toList());
		Collections.sort(elements, (e1, e2) -> Integer.compare(e2.count, e1.count));

		char[] chs = new char[S.length()];
		int index = 0;
		int offset = 2;
		for (Element element : elements) {
			for (int i = 0; i < element.count; i++) {
				while (!(index < chs.length && chs[index] == 0)) {
					if (index >= chs.length) {
						index = 0;
						offset = 1;
					} else {
						index += offset;
					}
				}

				chs[index] = element.ch;
			}
		}

		if (IntStream.range(0, chs.length - 1).allMatch(i -> chs[i] != chs[i + 1])) {
			return new String(chs);
		} else {
			return "";
		}
	}
}

class Element {
	char ch;
	int count;

	Element(char ch, int count) {
		this.ch = ch;
		this.count = count;
	}
}