class MyHashMap {
  private static final int SIZE = 1000001;

  private Integer[] values = new Integer[SIZE];

  /** value will always be positive. */
  public void put(int key, int value) {
    values[key] = value;
  }

  /**
   * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
   * for the key
   */
  public int get(int key) {
    return (values[key] == null) ? -1 : values[key];
  }

  /** Removes the mapping of the specified value key if this map contains a mapping for the key */
  public void remove(int key) {
    values[key] = null;
  }
}

// Your MyHashMap object will be instantiated and called as such:
// MyHashMap obj = new MyHashMap();
// obj.put(key,value);
// int param_2 = obj.get(key);
// obj.remove(key);
