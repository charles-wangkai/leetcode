class Solution {
  public long calculateScore(String[] instructions, int[] values) {
    int n = instructions.length;

    long result = 0;
    boolean[] visited = new boolean[n];
    int index = 0;
    while (index >= 0 && index < visited.length && !visited[index]) {
      visited[index] = true;

      if (instructions[index].equals("add")) {
        result += values[index];
        ++index;
      } else {
        index += values[index];
      }
    }

    return result;
  }
}