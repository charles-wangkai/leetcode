import java.util.NavigableMap;
import java.util.TreeMap;

class SnapshotArray {
  private int currentSnapId = 0;
  private NavigableMap<Integer, Integer>[] firstSnapIdToValueArray;

  @SuppressWarnings("unchecked")
  public SnapshotArray(int length) {
    firstSnapIdToValueArray = new TreeMap[length];
    for (int i = 0; i < firstSnapIdToValueArray.length; ++i) {
      firstSnapIdToValueArray[i] = new TreeMap<>();
      firstSnapIdToValueArray[i].put(0, 0);
    }
  }

  public void set(int index, int val) {
    firstSnapIdToValueArray[index].put(currentSnapId, val);
  }

  public int snap() {
    int result = currentSnapId;
    ++currentSnapId;

    return result;
  }

  public int get(int index, int snap_id) {
    return firstSnapIdToValueArray[index].floorEntry(snap_id).getValue();
  }
}

// Your SnapshotArray object will be instantiated and called as such:
// SnapshotArray obj = new SnapshotArray(length);
// obj.set(index,val);
// int param_2 = obj.snap();
// int param_3 = obj.get(index,snap_id);
