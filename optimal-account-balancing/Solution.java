import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	int minTransactionNum;

	public int minTransfers(int[][] transactions) {
		Map<Integer, Integer> person2balance = new HashMap<Integer, Integer>();

		for (int[] transaction : transactions) {
			updateMap(person2balance, transaction[0], -transaction[2]);
			updateMap(person2balance, transaction[1], transaction[2]);
		}

		List<Integer> balances = new ArrayList<Integer>();
		for (int balance : person2balance.values()) {
			balances.add(balance);
		}

		minTransactionNum = Integer.MAX_VALUE;
		search(balances, 0, 0);
		return minTransactionNum;
	}

	void updateMap(Map<Integer, Integer> map, int key, int delta) {
		map.put(key, map.getOrDefault(key, 0) + delta);

		if (map.get(key) == 0) {
			map.remove(key);
		}
	}

	void search(List<Integer> balances, int index, int transactionNum) {
		if (index == balances.size()) {
			minTransactionNum = Math.min(minTransactionNum, transactionNum);
			return;
		}

		if (balances.get(index) == 0) {
			search(balances, index + 1, transactionNum);
		}

		for (int i = index + 1; i < balances.size(); i++) {
			if (balances.get(index) * balances.get(i) < 0) {
				balances.set(i, balances.get(i) + balances.get(index));
				search(balances, index + 1, transactionNum + 1);
				balances.set(i, balances.get(i) - balances.get(index));
			}
		}
	}
}
