import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {
	// Constructor initializes an empty nested list.
	public NestedInteger() {
		throw new UnsupportedOperationException();
	}

	// Constructor initializes a single integer.
	public NestedInteger(int value) {
		throw new UnsupportedOperationException();
	}

	// @return true if this NestedInteger holds a single integer, rather than a
	// nested list.
	public boolean isInteger() {
		throw new UnsupportedOperationException();
	}

	// @return the single integer that this NestedInteger holds, if it holds a
	// single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger() {
		throw new UnsupportedOperationException();
	}

	// Set this NestedInteger to hold a single integer.
	public void setInteger(int value) {
		throw new UnsupportedOperationException();
	}

	// Set this NestedInteger to hold a nested list and adds a nested integer to
	// it.
	public void add(NestedInteger ni) {
		throw new UnsupportedOperationException();
	}

	// @return the nested list that this NestedInteger holds, if it holds a
	// nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList() {
		throw new UnsupportedOperationException();
	}
}

public class Solution {
	public NestedInteger deserialize(String s) {
		if (s.startsWith("[")) {
			NestedInteger ni = new NestedInteger();
			int beginIndex = 1;
			int stackSize = 0;
			for (int i = 1; i < s.length(); i++) {
				char ch = s.charAt(i);
				if (ch == '[') {
					stackSize++;
				} else if (i < s.length() - 1 && ch == ']') {
					stackSize--;
				} else if ((i == s.length() - 1 && beginIndex < i) || (ch == ',' && stackSize == 0)) {
					ni.add(deserialize(s.substring(beginIndex, i)));
					beginIndex = i + 1;
				}
			}
			return ni;
		} else {
			return new NestedInteger(Integer.parseInt(s));
		}
	}
}
