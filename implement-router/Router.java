import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Router {
  int memoryLimit;
  Queue<Packet> queue = new ArrayDeque<>();
  Set<Packet> packets = new HashSet<>();
  Map<Integer, RandomAccessQueue> destinationToRandomAccessQueue = new HashMap<>();

  public Router(int memoryLimit) {
    this.memoryLimit = memoryLimit;
  }

  public boolean addPacket(int source, int destination, int timestamp) {
    Packet packet = new Packet(source, destination, timestamp);
    if (packets.contains(packet)) {
      return false;
    }
    packets.add(packet);

    destinationToRandomAccessQueue.putIfAbsent(destination, new RandomAccessQueue());
    destinationToRandomAccessQueue.get(destination).offer(timestamp);

    queue.offer(packet);
    if (queue.size() == memoryLimit + 1) {
      remove();
    }

    return true;
  }

  public int[] forwardPacket() {
    if (queue.isEmpty()) {
      return new int[0];
    }

    Packet packet = remove();

    return new int[] {packet.source(), packet.destination(), packet.timestamp()};
  }

  public int getCount(int destination, int startTime, int endTime) {
    return destinationToRandomAccessQueue.containsKey(destination)
        ? destinationToRandomAccessQueue.get(destination).query(startTime, endTime)
        : 0;
  }

  private Packet remove() {
    Packet packet = queue.poll();
    packets.remove(packet);
    destinationToRandomAccessQueue.get(packet.destination()).poll();

    return packet;
  }
}

record Packet(int source, int destination, int timestamp) {}

class RandomAccessQueue {
  List<Integer> values = new ArrayList<>();
  int beginIndex = 0;

  void offer(int value) {
    values.add(value);
  }

  void poll() {
    ++beginIndex;
  }

  int query(int startTime, int endTime) {
    return findLastIndex(endTime) - findFirstIndex(startTime) + 1;
  }

  int findLastIndex(int endTime) {
    int result = beginIndex - 1;
    int lower = beginIndex;
    int upper = values.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (values.get(middle) <= endTime) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int findFirstIndex(int startTime) {
    int result = values.size();
    int lower = beginIndex;
    int upper = values.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (values.get(middle) >= startTime) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }
}

// Your Router object will be instantiated and called as such:
// Router obj = new Router(memoryLimit);
// boolean param_1 = obj.addPacket(source,destination,timestamp);
// int[] param_2 = obj.forwardPacket();
// int param_3 = obj.getCount(destination,startTime,endTime);
