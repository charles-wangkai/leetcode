import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minTransfers(int[][] transactions) {
    Map<Integer, Integer> personToBalance = new HashMap<>();
    for (int[] transaction : transactions) {
      updateMap(personToBalance, transaction[0], -transaction[2]);
      updateMap(personToBalance, transaction[1], transaction[2]);
    }

    return search(
        personToBalance.values().stream()
            .filter(balance -> balance != 0)
            .mapToInt(Integer::intValue)
            .toArray(),
        0,
        0);
  }

  int search(int[] balances, int index, int transactionNum) {
    if (index == balances.length) {
      return transactionNum;
    }

    if (balances[index] == 0) {
      return search(balances, index + 1, transactionNum);
    }

    int result = Integer.MAX_VALUE;
    for (int i = index + 1; i < balances.length; ++i) {
      if (balances[index] * balances[i] < 0) {
        balances[i] += balances[index];

        result = Math.min(result, search(balances, index + 1, transactionNum + 1));

        balances[i] -= balances[index];
      }
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> personToBalance, int person, int delta) {
    personToBalance.put(person, personToBalance.getOrDefault(person, 0) + delta);
    personToBalance.remove(person, 0);
  }
}
