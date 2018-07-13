public class MyHashSet {
	private static final int SIZE = 1000001;

	private boolean[] used = new boolean[SIZE];

	/** Initialize your data structure here. */
	public MyHashSet() {
	}

	public void add(int key) {
		used[key] = true;
	}

	public void remove(int key) {
		used[key] = false;
	}

	/** Returns true if this set did not already contain the specified element */
	public boolean contains(int key) {
		return used[key];
	}
}

// Your MyHashSet object will be instantiated and called as such:
// MyHashSet obj = new MyHashSet();
// obj.add(key);
// obj.remove(key);
// boolean param_3 = obj.contains(key);
