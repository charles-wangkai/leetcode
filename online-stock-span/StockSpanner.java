import java.util.Stack;

public class StockSpanner {
	private int index = 0;
	private Stack<Element> elements = new Stack<>();

	public StockSpanner() {
		elements.push(new Element(-1, Integer.MAX_VALUE));
	}

	public int next(int price) {
		while (price >= elements.peek().price) {
			elements.pop();
		}

		int result = index - elements.peek().index;

		elements.push(new Element(index, price));
		index++;

		return result;
	}
}

class Element {
	int index;
	int price;

	Element(int index, int price) {
		this.index = index;
		this.price = price;
	}
}

// Your StockSpanner object will be instantiated and called as such:
// StockSpanner obj = new StockSpanner();
// int param_1 = obj.next(price);
