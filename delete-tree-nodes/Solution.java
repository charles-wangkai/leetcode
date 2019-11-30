import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
		@SuppressWarnings("unchecked")
		List<Integer>[] childrens = new List[nodes];
		for (int i = 0; i < childrens.length; i++) {
			childrens[i] = new ArrayList<>();
		}
		for (int i = 0; i < parent.length; i++) {
			if (parent[i] != -1) {
				childrens[parent[i]].add(i);
			}
		}

		return search(childrens, value, 0).remainNum;
	}

	Element search(List<Integer>[] childrens, int[] value, int index) {
		int sum = value[index];
		int remainNum = 1;

		for (int child : childrens[index]) {
			Element subResult = search(childrens, value, child);

			sum += subResult.sum;
			remainNum += subResult.remainNum;
		}

		if (sum == 0) {
			remainNum = 0;
		}

		return new Element(sum, remainNum);
	}
}

class Element {
	int sum;
	int remainNum;

	Element(int sum, int remainNum) {
		this.sum = sum;
		this.remainNum = remainNum;
	}
}