import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2O {
	Lock lock = new ReentrantLock();
	Condition canReleaseH = lock.newCondition();
	Condition canReleaseO = lock.newCondition();

	int hCount = 0;
	int oCount = 0;
	int releaseCount = 0;
	int index;

	public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		lock.lock();
		try {
			hCount++;

			if (hCount >= 2 && oCount >= 1) {
				releaseCount++;
			}

			while (!(index < releaseCount * 3 && index % 3 != 2)) {
				canReleaseH.await();
			}

			// releaseHydrogen.run() outputs "H". Do not change or remove this line.
			releaseHydrogen.run();

			index++;
			canReleaseH.signal();
			canReleaseO.signal();
		} finally {
			lock.unlock();
		}
	}

	public void oxygen(Runnable releaseOxygen) throws InterruptedException {
		lock.lock();
		try {
			oCount++;

			if (hCount >= 2 && oCount >= 1) {
				releaseCount++;
			}

			while (!(index < releaseCount * 3 && index % 3 == 2)) {
				canReleaseO.await();
			}

			// releaseOxygen.run() outputs "H". Do not change or remove this line.
			releaseOxygen.run();

			index++;
			canReleaseH.signal();
			canReleaseO.signal();
		} finally {
			lock.unlock();
		}
	}
}
