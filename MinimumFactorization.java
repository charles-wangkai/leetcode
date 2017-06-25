import java.util.LinkedList;
import java.util.Queue;

public class MinimumFactorization {
	public int smallestFactorization(int a) {
		Queue<Element> candidates = new LinkedList<Element>();
		candidates.offer(new Element(0, 1, 1));
		while (!candidates.isEmpty()) {
			Element head = candidates.poll();

			for (int digit = head.lastDigit; digit <= 9; digit++) {
				long number = head.number * 10 + digit;
				int product = head.product * digit;

				if (number > Integer.MAX_VALUE || product > a || a % product != 0) {
					continue;
				}

				if (product == a) {
					return (int) number;
				}

				candidates.offer(new Element(number, digit, product));
			}
		}
		return 0;
	}
}

class Element {
	long number;
	int lastDigit;
	int product;

	Element(long number, int lastDigit, int product) {
		this.number = number;
		this.lastDigit = lastDigit;
		this.product = product;
	}
}