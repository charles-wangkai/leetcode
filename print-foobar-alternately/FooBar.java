import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {
	private int n;

	Lock lock = new ReentrantLock();
	Condition canRunFoo = lock.newCondition();
	Condition canRunBar = lock.newCondition();
	boolean fooOrBar = true;

	public FooBar(int n) {
		this.n = n;
	}

	public void foo(Runnable printFoo) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			lock.lock();
			try {
				while (!fooOrBar) {
					canRunFoo.await();
				}

				// printFoo.run() outputs "foo". Do not change or remove this line.
				printFoo.run();

				fooOrBar = false;
				canRunBar.signal();
			} finally {
				lock.unlock();
			}
		}
	}

	public void bar(Runnable printBar) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			lock.lock();
			try {
				while (fooOrBar) {
					canRunBar.await();
				}

				// printBar.run() outputs "bar". Do not change or remove this line.
				printBar.run();

				fooOrBar = true;
				canRunFoo.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
