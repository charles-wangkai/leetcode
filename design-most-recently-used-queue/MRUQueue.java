import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MRUQueue {
  List<Integer> queue;

  public MRUQueue(int n) {
    queue = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
  }

  public int fetch(int k) {
    queue.add(queue.remove(k - 1));

    return queue.get(queue.size() - 1);
  }
}

// Your MRUQueue object will be instantiated and called as such:
// MRUQueue obj = new MRUQueue(n);
// int param_1 = obj.fetch(k);
