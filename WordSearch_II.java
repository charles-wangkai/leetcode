import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TrieNode {
	// Initialize your data structure here.
	private Map<Character, TrieNode> children;

	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
	}

	public boolean hasChild(Character ch) {
		return children.containsKey(ch);
	}

	public TrieNode getChild(Character ch) {
		return children.get(ch);
	}

	public TrieNode putChild(Character ch) {
		if (!hasChild(ch)) {
			children.put(ch, new TrieNode());
		}
		return getChild(ch);
	}
}

class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public TrieNode getRoot() {
		return root;
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			node = node.putChild(word.charAt(i));
		}
		node.putChild(null);
	}
}

public class WordSearch_II {
	static final int[] OFFSET_R = { -1, 0, 1, 0 };
	static final int[] OFFSET_C = { 0, 1, 0, -1 };

	public List<String> findWords(char[][] board, String[] words) {
		List<String> result = new ArrayList<String>();

		int row = board.length;
		if (row == 0) {
			return result;
		}
		int col = board[0].length;
		if (col == 0) {
			return result;
		}

		Trie trie = new Trie();
		for (String word : words) {
			trie.insert(word);
		}

		Set<String> foundWords = new HashSet<String>();
		boolean[][] used = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				search(board, foundWords, trie.getRoot(), used, i, j,
						new StringBuilder());
			}
		}

		result.addAll(foundWords);
		return result;
	}

	void search(char[][] board, Set<String> foundWords, TrieNode node,
			boolean[][] used, int r, int c, StringBuilder sb) {
		int row = board.length;
		int col = board[0].length;
		if (!(r >= 0 && r < row && c >= 0 && c < col) || used[r][c]
				|| !node.hasChild(board[r][c])) {
			return;
		}

		TrieNode childNode = node.getChild(board[r][c]);
		sb.append(board[r][c]);

		if (childNode.hasChild(null)) {
			foundWords.add(sb.toString());
		}

		used[r][c] = true;
		for (int i = 0; i < OFFSET_R.length; i++) {
			search(board, foundWords, childNode, used, r + OFFSET_R[i], c
					+ OFFSET_C[i], sb);
		}
		used[r][c] = false;

		sb.deleteCharAt(sb.length() - 1);
	}
}
