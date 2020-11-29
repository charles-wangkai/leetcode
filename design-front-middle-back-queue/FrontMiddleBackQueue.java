import java.util.ArrayDeque;
import java.util.Deque;

class FrontMiddleBackQueue {
  Deque<Integer> leftHalf = new ArrayDeque<>();
  Deque<Integer> rightHalf = new ArrayDeque<>();

  private void balance() {
    if (leftHalf.size() > rightHalf.size()) {
      rightHalf.offerFirst(leftHalf.pollLast());
    } else if (leftHalf.size() + 2 == rightHalf.size()) {
      leftHalf.offerLast(rightHalf.pollFirst());
    }
  }

  private boolean isEmpty() {
    return leftHalf.isEmpty() && rightHalf.isEmpty();
  }

  public void pushFront(int val) {
    leftHalf.offerFirst(val);
    balance();
  }

  public void pushMiddle(int val) {
    leftHalf.offerLast(val);
    balance();
  }

  public void pushBack(int val) {
    rightHalf.offerLast(val);
    balance();
  }

  public int popFront() {
    if (isEmpty()) {
      return -1;
    }

    int result;
    if (!leftHalf.isEmpty()) {
      result = leftHalf.pollFirst();
    } else {
      result = rightHalf.pollFirst();
    }

    balance();

    return result;
  }

  public int popMiddle() {
    if (isEmpty()) {
      return -1;
    }

    int result;
    if (leftHalf.size() == rightHalf.size()) {
      result = leftHalf.pollLast();
    } else {
      result = rightHalf.pollFirst();
    }

    return result;
  }

  public int popBack() {
    if (isEmpty()) {
      return -1;
    }

    int result;
    if (!rightHalf.isEmpty()) {
      result = rightHalf.pollLast();
    } else {
      result = leftHalf.pollLast();
    }

    balance();

    return result;
  }
}

// Your FrontMiddleBackQueue object will be instantiated and called as such:
// FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
// obj.pushFront(val);
// obj.pushMiddle(val);
// obj.pushBack(val);
// int param_4 = obj.popFront();
// int param_5 = obj.popMiddle();
// int param_6 = obj.popBack();
