import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public int expressiveWords(String S, String[] words) {
		List<Group> sGroups = buildGroups(S);
		return (int) Arrays.stream(words).filter(word -> isStretchy(sGroups, buildGroups(word))).count();
	}

	List<Group> buildGroups(String s) {
		List<Group> groups = new ArrayList<Group>();
		char current = 0;
		int count = -1;
		for (int i = 0; i <= s.length(); i++) {
			if (i < s.length() && s.charAt(i) == current) {
				count++;
			} else {
				if (current != 0) {
					groups.add(new Group(current, count));
				}

				if (i < s.length()) {
					current = s.charAt(i);
					count = 1;
				}
			}
		}
		return groups;
	}

	boolean isStretchy(List<Group> sGroups, List<Group> wordGroups) {
		if (sGroups.size() != wordGroups.size()) {
			return false;
		}

		for (int i = 0; i < sGroups.size(); i++) {
			Group sGroup = sGroups.get(i);
			Group wordGroup = wordGroups.get(i);

			if (sGroup.letter != wordGroup.letter
					|| !(sGroup.count == wordGroup.count || (sGroup.count > wordGroup.count && sGroup.count >= 3))) {
				return false;
			}
		}
		return true;
	}
}

class Group {
	char letter;
	int count;

	Group(char letter, int count) {
		this.letter = letter;
		this.count = count;
	}
}