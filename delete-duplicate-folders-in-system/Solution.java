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
    TrieNode trie = new TrieNode(new TreeMap<>());
    for (List<String> path : paths) {
      addPath(trie, path);
    }

    Map<String, TrieNode> seen = new HashMap<>();
    Set<TrieNode> marked = new HashSet<>();
    dedup(seen, marked, trie);

    List<List<String>> rest = new ArrayList<>();
    search(rest, marked, trie, new ArrayList<>());

    return rest;
  }

  void search(List<List<String>> rest, Set<TrieNode> marked, TrieNode node, List<String> path) {
    if (!marked.contains(node)) {
      if (!path.isEmpty()) {
        rest.add(List.copyOf(path));
      }

      for (String folder : node.folderToChild().keySet()) {
        path.add(folder);
        search(rest, marked, node.folderToChild().get(folder), path);
        path.removeLast();
      }
    }
  }

  String dedup(Map<String, TrieNode> seen, Set<TrieNode> marked, TrieNode node) {
    StringBuilder sb = new StringBuilder("(");
    for (String folder : node.folderToChild().keySet()) {
      sb.append(folder).append(dedup(seen, marked, node.folderToChild().get(folder)));
    }
    sb.append(')');

    String result = sb.toString();

    if (!node.folderToChild().isEmpty()) {
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
      node.folderToChild().putIfAbsent(folder, new TrieNode(new TreeMap<>()));
      node = node.folderToChild().get(folder);
    }
  }
}

record TrieNode(SortedMap<String, TrieNode> folderToChild) {}
