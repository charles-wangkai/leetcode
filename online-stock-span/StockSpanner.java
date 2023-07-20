import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {
  private int index = 0;
  private Deque<Element> elements = new ArrayDeque<>();

  public StockSpanner() {
    elements.push(new Element(-1, Integer.MAX_VALUE));
  }

  public int next(int price) {
    while (price >= elements.peek().price()) {
      elements.pop();
    }

    int result = index - elements.peek().index();

    elements.push(new Element(index, price));
    ++index;

    return result;
  }
}

record Element(int index, int price) {}

// Your StockSpanner object will be instantiated and called as such:
// StockSpanner obj = new StockSpanner();
// int param_1 = obj.next(price);
