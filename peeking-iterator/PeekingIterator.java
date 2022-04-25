import java.util.Iterator;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
  private Iterator<Integer> iterator;
  private boolean hasNext;
  private Integer next;

  public PeekingIterator(Iterator<Integer> iterator) {
    this.iterator = iterator;
    move();
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    return next;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    Integer result = next;
    move();

    return result;
  }

  @Override
  public boolean hasNext() {
    return hasNext;
  }

  private void move() {
    if (iterator.hasNext()) {
      hasNext = true;
      next = iterator.next();
    } else {
      hasNext = false;
    }
  }
}
