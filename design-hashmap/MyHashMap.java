class MyHashMap {
  private static final int SIZE = 1_000_001;

  private Integer[] values = new Integer[SIZE];

  public void put(int key, int value) {
    values[key] = value;
  }

  public int get(int key) {
    return (values[key] == null) ? -1 : values[key];
  }

  public void remove(int key) {
    values[key] = null;
  }
}

// Your MyHashMap object will be instantiated and called as such:
// MyHashMap obj = new MyHashMap();
// obj.put(key,value);
// int param_2 = obj.get(key);
// obj.remove(key);
