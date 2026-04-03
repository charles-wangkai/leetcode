import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

class EventManager {
  Map<Integer, Integer> eventIdToPriority = new HashMap<>();
  SortedMap<Integer, SortedSet<Integer>> priorityToEventIds = new TreeMap<>();

  public EventManager(int[][] events) {
    for (int[] event : events) {
      add(event[0], event[1]);
    }
  }

  public void updatePriority(int eventId, int newPriority) {
    remove(eventId);
    add(eventId, newPriority);
  }

  public int pollHighest() {
    if (priorityToEventIds.isEmpty()) {
      return -1;
    }

    int priority = priorityToEventIds.lastKey();
    int eventId = priorityToEventIds.get(priority).first();
    remove(eventId);

    return eventId;
  }

  void remove(int eventId) {
    int priority = eventIdToPriority.remove(eventId);

    priorityToEventIds.get(priority).remove(eventId);
    if (priorityToEventIds.get(priority).isEmpty()) {
      priorityToEventIds.remove(priority);
    }
  }

  void add(int eventId, int priority) {
    eventIdToPriority.put(eventId, priority);

    priorityToEventIds.putIfAbsent(priority, new TreeSet<>());
    priorityToEventIds.get(priority).add(eventId);
  }
}

// Your EventManager object will be instantiated and called as such:
// EventManager obj = new EventManager(events);
// obj.updatePriority(eventId,newPriority);
// int param_2 = obj.pollHighest();
