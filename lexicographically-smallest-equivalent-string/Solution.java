import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public String smallestEquivalentString(String A, String B, String S) {
		Map<Character, Character> letterToParent = IntStream.range(0, 26).mapToObj(i -> (char) (i + 'a'))
				.collect(Collectors.toMap(Function.identity(), Function.identity()));

		for (int i = 0; i < A.length(); i++) {
			char rootA = findRoot(letterToParent, A.charAt(i));
			char rootB = findRoot(letterToParent, B.charAt(i));

			if (rootA < rootB) {
				letterToParent.put(rootB, rootA);
			} else if (rootA > rootB) {
				letterToParent.put(rootA, rootB);
			}
		}

		return S.chars().mapToObj(ch -> findRoot(letterToParent, (char) ch))
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	char findRoot(Map<Character, Character> letterToParent, char letter) {
		char root = letter;
		while (letterToParent.get(root) != root) {
			root = letterToParent.get(root);
		}

		char ch = letter;
		while (ch != root) {
			char next = letterToParent.get(ch);
			letterToParent.put(ch, root);

			ch = next;
		}

		return root;
	}
}
