import java.util.Arrays;

public class RelativeRanks {
	public String[] findRelativeRanks(int[] nums) {
		Element[] elements = new Element[nums.length];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = new Element(i, nums[i]);
		}

		Arrays.sort(elements, (e1, e2) -> e2.score - e1.score);

		String[] ranks = new String[elements.length];
		for (int i = 0; i < elements.length; i++) {
			String rank;
			if (i == 0) {
				rank = "Gold Medal";
			} else if (i == 1) {
				rank = "Silver Medal";
			} else if (i == 2) {
				rank = "Bronze Medal";
			} else {
				rank = String.valueOf(i + 1);
			}
			ranks[elements[i].index] = rank;
		}
		return ranks;
	}
}

class Element {
	int index;
	int score;

	Element(int index, int score) {
		this.index = index;
		this.score = score;
	}
}