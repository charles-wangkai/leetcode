import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

class TodoList {
  List<Task> tasks = new ArrayList<>();

  public int addTask(int userId, String taskDescription, int dueDate, List<String> tags) {
    tasks.add(new Task(userId, taskDescription, dueDate, Set.copyOf(tags), false));

    return tasks.size();
  }

  public List<String> getAllTasks(int userId) {
    return tasks.stream()
        .filter(task -> task.userId == userId && !task.completed)
        .sorted(Comparator.comparing(task -> task.dueDate))
        .map(task -> task.taskDescription)
        .toList();
  }

  public List<String> getTasksForTag(int userId, String tag) {
    return tasks.stream()
        .filter(task -> task.userId == userId && task.tags.contains(tag) && !task.completed)
        .sorted(Comparator.comparing(task -> task.dueDate))
        .map(task -> task.taskDescription)
        .toList();
  }

  public void completeTask(int userId, int taskId) {
    if (taskId <= tasks.size() && userId == tasks.get(taskId - 1).userId) {
      tasks.get(taskId - 1).completed = true;
    }
  }
}

class Task {
  int userId;
  String taskDescription;
  int dueDate;
  Set<String> tags;
  boolean completed;

  Task(int userId, String taskDescription, int dueDate, Set<String> tags, boolean completed) {
    this.userId = userId;
    this.taskDescription = taskDescription;
    this.dueDate = dueDate;
    this.tags = tags;
    this.completed = completed;
  }
}

// Your TodoList object will be instantiated and called as such:
// TodoList obj = new TodoList();
// int param_1 = obj.addTask(userId,taskDescription,dueDate,tags);
// List<String> param_2 = obj.getAllTasks(userId);
// List<String> param_3 = obj.getTasksForTag(userId,tag);
// obj.completeTask(userId,taskId);
