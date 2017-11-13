import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberOfAtoms {
	public String countOfAtoms(String formula) {
		Map<String, Integer> element2count = parse(formula);
		return element2count.keySet().stream().sorted()
				.map(element -> element + (element2count.get(element) == 1 ? "" : element2count.get(element)))
				.collect(Collectors.joining());
	}

	Map<String, Integer> parse(String s) {
		Map<String, Integer> element2count = new HashMap<String, Integer>();
		int index = 0;
		while (index < s.length()) {
			if (s.charAt(index) == '(') {
				int partLength = readPart(s, index);

				int countLength = readNumber(s, index + partLength);
				int count = (countLength == 0) ? 1
						: Integer.parseInt(s.substring(index + partLength, index + partLength + countLength));

				element2count = add(element2count,
						multiply(parse(s.substring(index + 1, index + partLength - 1)), count));

				index = index + partLength + countLength;
			} else {
				int elementLength = readElement(s, index);
				String element = s.substring(index, index + elementLength);
				index += elementLength;

				int countLength = readNumber(s, index);
				int count = (countLength == 0) ? 1 : Integer.parseInt(s.substring(index, index + countLength));
				index += countLength;

				element2count.put(element, element2count.getOrDefault(element, 0) + count);
			}
		}
		return element2count;
	}

	int readPart(String s, int index) {
		int depth = 1;
		index++;
		int length = 1;
		while (depth != 0) {
			char ch = s.charAt(index);
			if (ch == '(') {
				depth++;
			} else if (ch == ')') {
				depth--;
			}

			index++;
			length++;
		}
		return length;
	}

	int readNumber(String s, int index) {
		int length = 0;
		while (index < s.length() && Character.isDigit(s.charAt(index))) {
			index++;
			length++;
		}
		return length;
	}

	int readElement(String s, int index) {
		index++;
		int length = 1;
		while (index < s.length() && Character.isLowerCase(s.charAt(index))) {
			index++;
			length++;
		}
		return length;
	}

	Map<String, Integer> multiply(Map<String, Integer> element2count, int factor) {
		return element2count.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * factor));
	}

	Map<String, Integer> add(Map<String, Integer> element2count1, Map<String, Integer> element2count2) {
		return Stream.concat(element2count1.entrySet().stream(), element2count2.entrySet().stream())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (count1, count2) -> count1 + count2));
	}
}
