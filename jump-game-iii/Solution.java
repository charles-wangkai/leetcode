class Solution {
  public boolean canReach(int[] arr, int start) {
    return search(arr, new boolean[arr.length], start);
  }

  boolean search(int[] arr, boolean[] visited, int index) {
    if (index < 0 || index >= arr.length || visited[index]) {
      return false;
    }

    visited[index] = true;

    if (arr[index] == 0) {
      return true;
    }

    for (int offset : new int[] {-arr[index], arr[index]}) {
      if (search(arr, visited, index + offset)) {
        return true;
      }
    }

    return false;
  }
}
