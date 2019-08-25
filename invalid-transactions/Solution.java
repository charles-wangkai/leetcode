import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public List<String> invalidTransactions(String[] transactions) {
		return IntStream.range(0, transactions.length).filter(i -> {
			Transaction t = new Transaction(transactions[i]);

			if (t.amount > 1000) {
				return true;
			}

			for (int j = 0; j < transactions.length; j++) {
				if (j != i) {
					Transaction t1 = new Transaction(transactions[j]);

					if (Math.abs(t1.time - t.time) <= 60 && t1.name.equals(t.name) && !t1.city.equals(t.city)) {
						return true;
					}
				}
			}

			return false;
		}).mapToObj(i -> transactions[i]).collect(Collectors.toList());
	}
}

class Transaction {
	String name;
	int time;
	int amount;
	String city;

	Transaction(String s) {
		String[] fields = s.split(",");

		name = fields[0];
		time = Integer.parseInt(fields[1]);
		amount = Integer.parseInt(fields[2]);
		city = fields[3];
	}
}