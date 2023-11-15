import java.util.ArrayDeque;
import java.util.Queue;

class MovingAverage {
  Queue<Integer> values = new ArrayDeque<>();
  int sum;
  int size;

  public MovingAverage(int size) {
    this.size = size;
  }

  public double next(int val) {
    values.offer(val);
    sum += val;

    if (values.size() == size + 1) {
      sum -= values.poll();
    }

    return (double) sum / values.size();
  }
}

// Your MovingAverage object will be instantiated and called as such:
// MovingAverage obj = new MovingAverage(size);
// double param_1 = obj.next(val);
