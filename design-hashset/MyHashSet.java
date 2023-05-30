class MyHashSet {
  private static final int LIMIT = 1_000_000;

  private boolean[] seen = new boolean[LIMIT + 1];

  public void add(int key) {
    seen[key] = true;
  }

  public void remove(int key) {
    seen[key] = false;
  }

  public boolean contains(int key) {
    return seen[key];
  }
}

// Your MyHashSet object will be instantiated and called as such:
// MyHashSet obj = new MyHashSet();
// obj.add(key);
// obj.remove(key);
// boolean param_3 = obj.contains(key);
