import java.util.ArrayList;
import java.util.List;

public class AndroidUnlockPatterns {
	static final int KEY_SIZE = 9;

	static List<PassThrough> pts = new ArrayList<PassThrough>();
	static {
		pts.add(new PassThrough(1, 2, 3));
		pts.add(new PassThrough(4, 5, 6));
		pts.add(new PassThrough(7, 8, 9));
		pts.add(new PassThrough(1, 4, 7));
		pts.add(new PassThrough(2, 5, 8));
		pts.add(new PassThrough(3, 6, 9));
		pts.add(new PassThrough(1, 5, 9));
		pts.add(new PassThrough(3, 5, 7));
	}

	public int numberOfPatterns(int m, int n) {
		return search(m, n, new ArrayList<Integer>(), new boolean[KEY_SIZE + 1]);
	}

	int search(int m, int n, List<Integer> keys, boolean[] used) {
		if (keys.size() == n) {
			return 0;
		}

		int result = 0;
		for (int key = 1; key <= KEY_SIZE; key++) {
			if (!used[key] && isValid(keys, key)) {
				keys.add(key);
				used[key] = true;

				if (keys.size() >= m) {
					result++;
				}

				result += search(m, n, keys, used);

				used[key] = false;
				keys.remove(keys.size() - 1);
			}
		}
		return result;
	}

	boolean isValid(List<Integer> keys, int key) {
		if (keys.isEmpty()) {
			return true;
		}

		int prevKey = keys.get(keys.size() - 1);
		for (PassThrough pt : pts) {
			if (((pt.key1 == prevKey && pt.key2 == key) || (pt.key1 == key && pt.key2 == prevKey))
					&& !keys.contains(pt.through)) {
				return false;
			}
		}

		return true;
	}
}

class PassThrough {
	int key1;
	int through;
	int key2;

	PassThrough(int key1, int through, int key2) {
		this.key1 = key1;
		this.through = through;
		this.key2 = key2;
	}
}