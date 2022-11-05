import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public List<String> findWords(char[][] board, String[] words) {
    int m = board.length;
    int n = board[0].length;

    Trie trie = new Trie();
    for (String word : words) {
      insert(trie, word);
    }

    Set<String> foundWords = new HashSet<>();
    boolean[][] visited = new boolean[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        search(board, foundWords, trie, visited, r, c, new StringBuilder());
      }
    }

    return List.copyOf(foundWords);
  }

  void search(
      char[][] board,
      Set<String> foundWords,
      Trie node,
      boolean[][] visited,
      int r,
      int c,
      StringBuilder current) {
    int m = board.length;
    int n = board[0].length;

    if (r >= 0 && r < m && c >= 0 && c < n && !visited[r][c] && node.hasChild(board[r][c])) {
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
  }

  void insert(Trie node, String word) {
    for (char letter : word.toCharArray()) {
      node.addChild(letter);
      node = node.getChild(letter);
    }

    node.addChild(null);
  }
}

class Trie {
  private Map<Character, Trie> letterToChild = new HashMap<>();

  public boolean hasChild(Character letter) {
    return letterToChild.containsKey(letter);
  }

  public Trie getChild(Character letter) {
    return letterToChild.get(letter);
  }

  public void addChild(Character letter) {
    if (!hasChild(letter)) {
      letterToChild.put(letter, new Trie());
    }
  }
}
