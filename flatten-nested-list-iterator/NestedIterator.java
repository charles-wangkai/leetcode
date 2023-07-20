import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {
  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return empty list if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
  Deque<Iterator<NestedInteger>> iterators = new ArrayDeque<>();
  Integer nextValue;

  public NestedIterator(List<NestedInteger> nestedList) {
    iterators.push(nestedList.iterator());
    move();
  }

  @Override
  public Integer next() {
    int result = nextValue;
    nextValue = null;
    move();

    return result;
  }

  @Override
  public boolean hasNext() {
    return nextValue != null;
  }

  private void move() {
    while (nextValue == null && !iterators.isEmpty()) {
      if (iterators.peek().hasNext()) {
        NestedInteger ni = iterators.peek().next();
        if (ni.isInteger()) {
          nextValue = ni.getInteger();
        } else {
          iterators.push(ni.getList().iterator());
        }
      } else {
        iterators.pop();
      }
    }
  }
}

// Your NestedIterator object will be instantiated and called as such:
// NestedIterator i = new NestedIterator(nestedList);
// while (i.hasNext()) v[f()] = i.next();
