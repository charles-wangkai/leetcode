import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  public int maxStudentsOnBench(int[][] students) {
    Map<Integer, Set<Integer>> benchIdToStudentIds = new HashMap<>();
    for (int[] student : students) {
      benchIdToStudentIds.putIfAbsent(student[1], new HashSet<>());
      benchIdToStudentIds.get(student[1]).add(student[0]);
    }

    return benchIdToStudentIds.values().stream().mapToInt(Set::size).max().orElse(0);
  }
}