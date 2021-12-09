class Solution {
  public boolean canReach(int[] arr, int start) {
    return search(arr, new boolean[arr.length], start);
  }

  boolean search(int[] arr, boolean[] visited, int index) {
    if (index < 0 || index >= arr.length || visited[index]) {
      return false;
    }

    visited[index] = true;

    return arr[index] == 0
        || search(arr, visited, index - arr[index])
        || search(arr, visited, index + arr[index]);
  }
}
