import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

class TaskManager {
  SortedMap<Integer, SortedSet<Integer>> priorityToTaskIds = new TreeMap<>();
  Map<Integer, Integer> taskIdToUserId = new HashMap<>();
  Map<Integer, Integer> taskIdToPriority = new HashMap<>();

  public TaskManager(List<List<Integer>> tasks) {
    for (List<Integer> task : tasks) {
      add(task.get(0), task.get(1), task.get(2));
    }
  }

  public void add(int userId, int taskId, int priority) {
    priorityToTaskIds.putIfAbsent(priority, new TreeSet<>());
    priorityToTaskIds.get(priority).add(taskId);

    taskIdToUserId.put(taskId, userId);
    taskIdToPriority.put(taskId, priority);
  }

  public void edit(int taskId, int newPriority) {
    int userId = taskIdToUserId.get(taskId);
    rmv(taskId);
    add(userId, taskId, newPriority);
  }

  public void rmv(int taskId) {
    int priority = taskIdToPriority.remove(taskId);

    priorityToTaskIds.get(priority).remove(taskId);
    if (priorityToTaskIds.get(priority).isEmpty()) {
      priorityToTaskIds.remove(priority);
    }

    taskIdToUserId.remove(taskId);
  }

  public int execTop() {
    if (priorityToTaskIds.isEmpty()) {
      return -1;
    }

    int taskId = priorityToTaskIds.lastEntry().getValue().last();
    int userId = taskIdToUserId.get(taskId);
    rmv(taskId);

    return userId;
  }
}

// Your TaskManager object will be instantiated and called as such:
// TaskManager obj = new TaskManager(tasks);
// obj.add(userId,taskId,priority);
// obj.edit(taskId,newPriority);
// obj.rmv(taskId);
// int param_4 = obj.execTop();
