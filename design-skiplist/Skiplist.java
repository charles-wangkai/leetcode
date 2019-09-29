import java.util.Random;

public class Skiplist {
	Random random = new Random();
	int maxLevel = 0;
	Node topHead = new Node(Integer.MIN_VALUE, null, null);

	public boolean search(int target) {
		Node prev = topHead;
		while (prev != null) {
			while (prev.right != null && prev.right.value < target) {
				prev = prev.right;
			}

			if (prev.right != null && prev.right.value == target) {
				return true;
			}

			prev = prev.down;
		}

		return false;
	}

	public void add(int num) {
		int beginLevel = 0;
		while (true) {
			if (random.nextInt(2) != 0) {
				break;
			}

			beginLevel++;
		}

		while (maxLevel < beginLevel) {
			maxLevel++;

			Node node = new Node(Integer.MIN_VALUE, null, topHead);
			topHead = node;
		}

		Node prev = topHead;
		Node top = null;
		for (int level = maxLevel; level >= 0; level--) {
			while (prev.right != null && prev.right.value < num) {
				prev = prev.right;
			}

			if (level <= beginLevel) {
				Node node = new Node(num, prev.right, null);

				if (top != null) {
					top.down = node;
				}

				prev.right = node;
				top = node;
			}

			prev = prev.down;
		}
	}

	public boolean erase(int num) {
		boolean result = false;

		Node prev = topHead;
		while (prev != null) {
			while (prev.right != null && prev.right.value < num) {
				prev = prev.right;
			}

			if (prev.right != null && prev.right.value == num) {
				prev.right = prev.right.right;

				result = true;
			}

			prev = prev.down;
		}

		return result;
	}
}

class Node {
	int value;
	Node right;
	Node down;

	Node(int value, Node right, Node down) {
		this.value = value;
		this.right = right;
		this.down = down;
	}
}

// Your Skiplist object will be instantiated and called as such:
// Skiplist obj = new Skiplist();
// boolean param_1 = obj.search(target);
// obj.add(num);
// boolean param_3 = obj.erase(num);
