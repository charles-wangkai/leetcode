import java.util.Arrays;
import java.util.stream.Collectors;

public class FindAndReplaceInString {
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		String[] replaced = new String[S.length()];
		Arrays.fill(replaced, "");

		for (int i = 0; i < replaced.length;) {
			int index = Arrays.stream(indexes).boxed().collect(Collectors.toList()).indexOf(i);
			if (index >= 0 && S.startsWith(sources[index], i)) {
				replaced[i] = targets[index];

				i += sources[index].length();
			} else {
				replaced[i] = String.valueOf(S.charAt(i));

				i++;
			}
		}

		return Arrays.stream(replaced).collect(Collectors.joining(""));
	}
}
