import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LockingTree {
  int[] parent;
  int[] lockUsers;
  List<Integer>[] childrens;

  @SuppressWarnings("unchecked")
  public LockingTree(int[] parent) {
    int n = parent.length;

    this.parent = parent;

    lockUsers = new int[n];
    Arrays.fill(lockUsers, -1);

    childrens = new List[n];
    for (int i = 0; i < childrens.length; ++i) {
      childrens[i] = new ArrayList<>();
    }

    for (int i = 1; i < parent.length; ++i) {
      childrens[parent[i]].add(i);
    }
  }

  public boolean lock(int num, int user) {
    if (lockUsers[num] != -1) {
      return false;
    }

    lockUsers[num] = user;

    return true;
  }

  public boolean unlock(int num, int user) {
    if (lockUsers[num] != user) {
      return false;
    }

    lockUsers[num] = -1;

    return true;
  }

  public boolean upgrade(int num, int user) {
    if (lockUsers[num] != -1 || !hasLockedDescendants(num) || hasLockedAncestors(num)) {
      return false;
    }

    lock(num, user);
    unlockDescendants(num);

    return true;
  }

  boolean hasLockedDescendants(int node) {
    for (int child : childrens[node]) {
      if (lockUsers[child] != -1 || hasLockedDescendants(child)) {
        return true;
      }
    }

    return false;
  }

  boolean hasLockedAncestors(int node) {
    for (int p = parent[node]; p != -1; p = parent[p]) {
      if (lockUsers[p] != -1) {
        return true;
      }
    }

    return false;
  }

  void unlockDescendants(int node) {
    for (int child : childrens[node]) {
      unlock(child, lockUsers[child]);

      unlockDescendants(child);
    }
  }
}

// Your LockingTree object will be instantiated and called as such:
// LockingTree obj = new LockingTree(parent);
// boolean param_1 = obj.lock(num,user);
// boolean param_2 = obj.unlock(num,user);
// boolean param_3 = obj.upgrade(num,user);
