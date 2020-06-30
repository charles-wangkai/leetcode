import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public List<String> findWords(char[][] board, String[] words) {
		int row = board.length;
		if (row == 0) {
			return Collections.emptyList();
		}
		int col = board[0].length;
		if (col == 0) {
			return Collections.emptyList();
		}

		Trie trie = new Trie();
		for (String word : words) {
			insert(trie, word);
		}

		Set<String> foundWords = new HashSet<>();
		boolean[][] visited = new boolean[row][col];
		for (int r = 0; r < row; ++r) {
			for (int c = 0; c < col; ++c) {
				search(board, foundWords, trie, visited, r, c, new StringBuilder());
			}
		}

		return new ArrayList<>(foundWords);
	}

	void search(char[][] board, Set<String> foundWords, Trie node, boolean[][] visited, int r, int c,
			StringBuilder current) {
		int row = board.length;
		int col = board[0].length;

		if (!(r >= 0 && r < row && c >= 0 && c < col) || visited[r][c] || !node.hasChild(board[r][c])) {
			return;
		}

		Trie child = node.getChild(board[r][c]);
		current.append(board[r][c]);

		if (child.hasChild(null)) {
			foundWords.add(current.toString());
		}

		visited[r][c] = true;
		for (int i = 0; i < R_OFFSETS.length; ++i) {
			search(board, foundWords, child, visited, r + R_OFFSETS[i], c + C_OFFSETS[i], current);
		}
		visited[r][c] = false;

		current.deleteCharAt(current.length() - 1);
	}

	void insert(Trie node, String word) {
		for (int i = 0; i < word.length(); ++i) {
			node.addChild(word.charAt(i));
			node = node.getChild(word.charAt(i));
		}

		node.addChild(null);
	}
}

class Trie {
	private Map<Character, Trie> children = new HashMap<>();

	public boolean hasChild(Character ch) {
		return children.containsKey(ch);
	}

	public Trie getChild(Character ch) {
		return children.get(ch);
	}

	public void addChild(Character ch) {
		if (!hasChild(ch)) {
			children.put(ch, new Trie());
		}
	}
}
