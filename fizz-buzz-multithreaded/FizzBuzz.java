import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzz {
	private int n;

	Lock lock = new ReentrantLock();
	Condition canRunFizz = lock.newCondition();
	Condition canRunBuzz = lock.newCondition();
	Condition canRunFizzbuzz = lock.newCondition();
	Condition canRunNumber = lock.newCondition();
	int current = 1;

	public FizzBuzz(int n) {
		this.n = n;
	}

	// printFizz.run() outputs "fizz".
	public void fizz(Runnable printFizz) throws InterruptedException {
		while (true) {
			lock.lock();
			try {
				while (current <= n && !(current % 3 == 0 && current % 5 != 0)) {
					canRunFizz.await();
				}

				if (current > n) {
					break;
				}

				printFizz.run();

				current++;
				canRunBuzz.signal();
				canRunFizzbuzz.signal();
				canRunNumber.signal();
			} finally {
				lock.unlock();
			}
		}
	}

	// printBuzz.run() outputs "buzz".
	public void buzz(Runnable printBuzz) throws InterruptedException {
		while (true) {
			lock.lock();
			try {
				while (current <= n && !(current % 5 == 0 && current % 3 != 0)) {
					canRunBuzz.await();
				}

				if (current > n) {
					break;
				}

				printBuzz.run();

				current++;
				canRunFizz.signal();
				canRunFizzbuzz.signal();
				canRunNumber.signal();
			} finally {
				lock.unlock();
			}
		}
	}

	// printFizzBuzz.run() outputs "fizzbuzz".
	public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
		while (true) {
			lock.lock();
			try {
				while (current <= n && current % 15 != 0) {
					canRunFizzbuzz.await();
				}

				if (current > n) {
					break;
				}

				printFizzBuzz.run();

				current++;
				canRunFizz.signal();
				canRunBuzz.signal();
				canRunNumber.signal();
			} finally {
				lock.unlock();
			}
		}
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void number(IntConsumer printNumber) throws InterruptedException {
		while (true) {
			lock.lock();
			try {
				while (current <= n && !(current % 3 != 0 && current % 5 != 0)) {
					canRunNumber.await();
				}

				if (current > n) {
					break;
				}

				printNumber.accept(current);

				current++;
				canRunFizz.signal();
				canRunBuzz.signal();
				canRunFizzbuzz.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}