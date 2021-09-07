class Solution {
  static final int MODULUS = 1_000_000_007;

  public int firstDayBeenInAllRooms(int[] nextVisit) {
    int n = nextVisit.length;

    int result = 0;
    int[] times = new int[n];
    for (int i = 1; i < n; ++i) {
      result = addMod(result, search(nextVisit, times, i, i - 1));
    }

    return result;
  }

  int search(int[] nextVisit, int[] times, int target, int room) {
    if (room == target - 1) {
      if (nextVisit[room] == target) {
        return times[room];
      } else if (nextVisit[room] == target - 1) {
        times[room] = 2;
      } else {
        times[room] = addMod(search(nextVisit, times, target - 1, nextVisit[room]), 2);
      }
    } else {
      times[room] = addMod(search(nextVisit, times, target, nextVisit[room]), times[room]);
    }

    nextVisit[room] = target;

    return times[room];
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}
