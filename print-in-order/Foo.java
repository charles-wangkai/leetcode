import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {
  boolean firstDone;
  boolean secondDone;

  Lock lock = new ReentrantLock();
  Condition canRunSecond = lock.newCondition();
  Condition canRunThird = lock.newCondition();

  public void first(Runnable printFirst) {
    lock.lock();
    try {
      // printFirst.run() outputs "first". Do not change or remove this line.
      printFirst.run();

      firstDone = true;
      canRunSecond.signal();
    } finally {
      lock.unlock();
    }
  }

  public void second(Runnable printSecond) throws InterruptedException {
    lock.lock();
    try {
      while (!firstDone) {
        canRunSecond.await();
      }

      // printSecond.run() outputs "second". Do not change or remove this line.
      printSecond.run();

      secondDone = true;
      canRunThird.signal();
    } finally {
      lock.unlock();
    }
  }

  public synchronized void third(Runnable printThird) throws InterruptedException {
    lock.lock();
    try {
      while (!secondDone) {
        canRunThird.await();
      }

      // printThird.run() outputs "third". Do not change or remove this line.
      printThird.run();
    } finally {
      lock.unlock();
    }
  }
}
