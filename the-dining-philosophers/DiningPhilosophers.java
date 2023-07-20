class DiningPhilosophers {
  // call the run() method of any runnable to execute its code
  public void wantsToEat(
      int philosopher,
      Runnable pickLeftFork,
      Runnable pickRightFork,
      Runnable eat,
      Runnable putLeftFork,
      Runnable putRightFork) {
    synchronized (this) {
      pickLeftFork.run();
      pickRightFork.run();
      eat.run();
      putLeftFork.run();
      putRightFork.run();
    }
  }
}
