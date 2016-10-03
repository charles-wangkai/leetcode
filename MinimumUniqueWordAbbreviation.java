import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumUniqueWordAbbreviation {
	String result;

	public String minAbbreviation(String target, String[] dictionary) {
		Set<Integer> potentials = buildPotentials(target, dictionary);

		result = null;
		search(target, potentials, new ArrayList<Integer>(), Math.min(target.length(), 21), 0);
		return result;
	}

	void search(String target, Set<Integer> potentials, List<Integer> chosen, int remain, int index) {
		if (isValid(chosen, potentials)) {
			String abbr = buildAbbr(target, chosen);
			if (result == null || computeLength(abbr) < computeLength(result)) {
				result = abbr;
			}
			return;
		}

		if (remain == 0 || index == target.length()) {
			return;
		}

		for (int i = index; i < target.length(); i++) {
			chosen.add(i);
			search(target, potentials, chosen, remain - 1, i + 1);
			chosen.remove(chosen.size() - 1);
		}
	}

	boolean isValid(List<Integer> chosen, Set<Integer> potentials) {
		int mask = buildMask(chosen);
		for (int potential : potentials) {
			if ((mask & potential) == mask) {
				return false;
			}
		}
		return true;
	}

	int buildMask(List<Integer> chosen) {
		int mask = 0;
		for (int index : chosen) {
			mask += 1 << index;
		}
		return mask;
	}

	int computeLength(String abbr) {
		int length = 0;
		int index = 0;
		while (index < abbr.length()) {
			if (Character.isDigit(abbr.charAt(index))) {
				while (index < abbr.length() && Character.isDigit(abbr.charAt(index))) {
					index++;
				}
			} else {
				index++;
			}
			length++;
		}
		return length;
	}

	String buildAbbr(String target, List<Integer> chosen) {
		StringBuilder sb = new StringBuilder();
		int prevIndex = -1;
		for (int index : chosen) {
			int gap = index - prevIndex - 1;
			if (gap > 0) {
				sb.append(gap);
			}

			sb.append(target.charAt(index));

			prevIndex = index;
		}

		int gap = target.length() - prevIndex - 1;
		if (gap > 0) {
			sb.append(gap);
		}

		return sb.toString();
	}

	Set<Integer> buildPotentials(String target, String[] dictionary) {
		Set<Integer> potentials = new HashSet<Integer>();
		for (String word : dictionary) {
			if (word.length() == target.length()) {
				potentials.add(diff(target, word));
			}
		}
		return potentials;
	}

	int diff(String target, String word) {
		int code = 0;
		for (int i = 0; i < target.length(); i++) {
			if (target.charAt(i) == word.charAt(i)) {
				code += 1 << i;
			}
		}
		return code;
	}
}
