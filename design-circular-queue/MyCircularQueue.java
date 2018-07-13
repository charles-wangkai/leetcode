public class MyCircularQueue {
	private int[] data;
	private int beginIndex;
	private int itemNum;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public MyCircularQueue(int k) {
		data = new int[k];
		beginIndex = 0;
		itemNum = 0;
	}

	/**
	 * Insert an element into the circular queue. Return true if the operation is
	 * successful.
	 */
	public boolean enQueue(int value) {
		if (isFull()) {
			return false;
		}

		data[(beginIndex + itemNum) % data.length] = value;
		itemNum++;

		return true;
	}

	/**
	 * Delete an element from the circular queue. Return true if the operation is
	 * successful.
	 */
	public boolean deQueue() {
		if (isEmpty()) {
			return false;
		}

		beginIndex = (beginIndex + 1) % data.length;
		itemNum--;

		return true;
	}

	/** Get the front item from the queue. */
	public int Front() {
		return isEmpty() ? -1 : data[beginIndex];
	}

	/** Get the last item from the queue. */
	public int Rear() {
		return isEmpty() ? -1 : data[(beginIndex + itemNum - 1) % data.length];
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return itemNum == 0;
	}

	/** Checks whether the circular queue is full or not. */
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
