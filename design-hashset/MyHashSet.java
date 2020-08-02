class MyHashSet {
	private static final int SIZE = 1_000_001;

	private boolean[] used = new boolean[SIZE];

	public void add(int key) {
		used[key] = true;
	}

	public void remove(int key) {
		used[key] = false;
	}

	/** Returns true if this set contains the specified element */
	public boolean contains(int key) {
		return used[key];
	}
}

// Your MyHashSet object will be instantiated and called as such:
// MyHashSet obj = new MyHashSet();
// obj.add(key);
// obj.remove(key);
// boolean param_3 = obj.contains(key);
