import java.util.stream.IntStream;

public class DiningPhilosophers {
	static final int SIZE = 5;

	Object[] forks = IntStream.range(0, SIZE).mapToObj(i -> new Object()).toArray(Object[]::new);

	// call the run() method of any runnable to execute its code
	public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
			Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
		Object leftFork = forks[(philosopher + 1) % SIZE];
		Object rightFork = forks[philosopher];

		if (philosopher == SIZE - 1) {
			synchronized (rightFork) {
				pickRightFork.run();

				synchronized (leftFork) {
					pickLeftFork.run();

					eat.run();

					putLeftFork.run();
				}

				putRightFork.run();
			}
		} else {
			synchronized (leftFork) {
				pickLeftFork.run();

				synchronized (rightFork) {
					pickRightFork.run();

					eat.run();

					putRightFork.run();
				}

				putLeftFork.run();
			}
		}
	}
}
