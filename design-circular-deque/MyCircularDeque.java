class MyCircularDeque {
  private int[] values;
  private int beginIndex;
  private int size;

  public MyCircularDeque(int k) {
    values = new int[k];
  }

  public boolean insertFront(int value) {
    if (isFull()) {
      return false;
    }

    beginIndex = Math.floorMod(beginIndex - 1, values.length);
    values[beginIndex] = value;
    ++size;

    return true;
  }

  public boolean insertLast(int value) {
    if (isFull()) {
      return false;
    }

    values[(beginIndex + size) % values.length] = value;
    ++size;

    return true;
  }

  public boolean deleteFront() {
    if (isEmpty()) {
      return false;
    }

    beginIndex = (beginIndex + 1) % values.length;
    --size;

    return true;
  }

  public boolean deleteLast() {
    if (isEmpty()) {
      return false;
    }

    --size;

    return true;
  }

  public int getFront() {
    return isEmpty() ? -1 : values[beginIndex];
  }

  public int getRear() {
    return isEmpty() ? -1 : values[(beginIndex + size - 1) % values.length];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == values.length;
  }
}

// Your MyCircularDeque object will be instantiated and called as such:
// MyCircularDeque obj = new MyCircularDeque(k);
// boolean param_1 = obj.insertFront(value);
// boolean param_2 = obj.insertLast(value);
// boolean param_3 = obj.deleteFront();
// boolean param_4 = obj.deleteLast();
// int param_5 = obj.getFront();
// int param_6 = obj.getRear();
// boolean param_7 = obj.isEmpty();
// boolean param_8 = obj.isFull();
