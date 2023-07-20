import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {
  int capacity;
  Queue<Integer> queue = new LinkedList<>();

  Lock lock = new ReentrantLock();
  Condition canRunEnqueue = lock.newCondition();
  Condition canRunDequeue = lock.newCondition();

  public BoundedBlockingQueue(int capacity) {
    this.capacity = capacity;
  }

  public void enqueue(int element) throws InterruptedException {
    lock.lock();
    try {
      while (queue.size() == capacity) {
        canRunEnqueue.await();
      }

      queue.offer(element);

      canRunDequeue.signal();
    } finally {
      lock.unlock();
    }
  }

  public int dequeue() throws InterruptedException {
    lock.lock();
    try {
      while (queue.isEmpty()) {
        canRunDequeue.await();
      }

      int result = queue.poll();

      canRunEnqueue.signal();

      return result;
    } finally {
      lock.unlock();
    }
  }

  public int size() {
    return queue.size();
  }
}
