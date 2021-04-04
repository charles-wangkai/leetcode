class MyCircularQueue {
  private int[] data;
  private int beginIndex = 0;
  private int itemNum = 0;

  public MyCircularQueue(int k) {
    data = new int[k];
  }

  public boolean enQueue(int value) {
    if (isFull()) {
      return false;
    }

    data[(beginIndex + itemNum) % data.length] = value;
    ++itemNum;

    return true;
  }

  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    }

    beginIndex = (beginIndex + 1) % data.length;
    --itemNum;

    return true;
  }

  public int Front() {
    return isEmpty() ? -1 : data[beginIndex];
  }

  public int Rear() {
    return isEmpty() ? -1 : data[(beginIndex + itemNum - 1) % data.length];
  }

  public boolean isEmpty() {
    return itemNum == 0;
  }

  public boolean isFull() {
    return itemNum == data.length;
  }
}

// Your MyCircularQueue object will be instantiated and called as such:
// MyCircularQueue obj = new MyCircularQueue(k);
// boolean param_1 = obj.enQueue(value);
// boolean param_2 = obj.deQueue();
// int param_3 = obj.Front();
// int param_4 = obj.Rear();
// boolean param_5 = obj.isEmpty();
// boolean param_6 = obj.isFull();
