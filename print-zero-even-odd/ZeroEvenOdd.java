import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
	private int n;

	Lock lock = new ReentrantLock();
	Condition canRunZero = lock.newCondition();
	Condition canRunEven = lock.newCondition();
	Condition canRunOdd = lock.newCondition();
	int count = 0;

	public ZeroEvenOdd(int n) {
		this.n = n;
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void zero(IntConsumer printNumber) throws InterruptedException {
		while (true) {
			lock.lock();
			try {
				while (count < 2 * n && count % 2 != 0) {
					canRunZero.await();
				}

				canRunEven.signal();
				canRunOdd.signal();

				if (count == 2 * n) {
					break;
				}

				printNumber.accept(0);

				count++;
			} finally {
				lock.unlock();
			}
		}
	}

	public void even(IntConsumer printNumber) throws InterruptedException {
		while (true) {
			lock.lock();
			try {
				while (count < 2 * n && count % 4 != 3) {
					canRunEven.await();
				}

				canRunZero.signal();

				if (count == 2 * n) {
					break;
				}

				printNumber.accept((count + 1) / 2);

				count++;
			} finally {
				lock.unlock();
			}
		}
	}

	public void odd(IntConsumer printNumber) throws InterruptedException {
		while (true) {
			lock.lock();
			try {
				while (count < 2 * n && count % 4 != 1) {
					canRunOdd.await();
				}

				canRunZero.signal();

				if (count == 2 * n) {
					break;
				}

				printNumber.accept((count + 1) / 2);

				count++;
			} finally {
				lock.unlock();
			}
		}
	}
}
