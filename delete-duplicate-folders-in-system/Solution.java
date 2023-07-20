// https://leetcode.com/problems/delete-duplicate-folders-in-system/discuss/1360768

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
    TrieNode trie = new TrieNode();
    for (List<String> path : paths) {
      addPath(trie, path);
    }

    Map<String, TrieNode> seen = new HashMap<>();
    Set<TrieNode> marked = new HashSet<>();
    dedup(seen, marked, trie);

    List<List<String>> remaining = new ArrayList<>();
    search(remaining, marked, trie, new ArrayList<>());

    return remaining;
  }

  void search(
      List<List<String>> remaining, Set<TrieNode> marked, TrieNode node, List<String> path) {
    if (!marked.contains(node)) {
      if (!path.isEmpty()) {
        remaining.add(List.copyOf(path));
      }

      for (String folder : node.folderToChild.keySet()) {
        path.add(folder);
        search(remaining, marked, node.folderToChild.get(folder), path);
        path.remove(path.size() - 1);
      }
    }
  }

  String dedup(Map<String, TrieNode> seen, Set<TrieNode> marked, TrieNode node) {
    StringBuilder sb = new StringBuilder("(");
    for (String folder : node.folderToChild.keySet()) {
      sb.append(folder).append(dedup(seen, marked, node.folderToChild.get(folder)));
    }
    sb.append(')');

    String result = sb.toString();

    if (!node.folderToChild.isEmpty()) {
      if (seen.containsKey(result)) {
        marked.add(seen.get(result));
        marked.add(node);
      } else {
        seen.put(result, node);
      }
    }

    return result;
  }

  void addPath(TrieNode trie, List<String> path) {
    TrieNode node = trie;
    for (String folder : path) {
      if (!node.folderToChild.containsKey(folder)) {
        node.folderToChild.put(folder, new TrieNode());
      }

      node = node.folderToChild.get(folder);
    }
  }
}

class TrieNode {
  SortedMap<String, TrieNode> folderToChild = new TreeMap<>();
}
