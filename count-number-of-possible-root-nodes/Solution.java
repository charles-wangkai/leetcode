import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int rootCount(int[][] edges, int[][] guesses, int k) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    Set<Guess> guessSet =
        Arrays.stream(guesses)
            .map(guess -> new Guess(guess[0], guess[1]))
            .collect(Collectors.toSet());

    return search(k, adjLists, guessSet, -1, 0, countCorrect(adjLists, guessSet, -1, 0));
  }

  int search(
      int k,
      List<Integer>[] adjLists,
      Set<Guess> guessSet,
      int parent,
      int node,
      int correctCount) {
    int result = (correctCount >= k) ? 1 : 0;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        result +=
            search(
                k,
                adjLists,
                guessSet,
                node,
                adj,
                correctCount
                    - (guessSet.contains(new Guess(node, adj)) ? 1 : 0)
                    + (guessSet.contains(new Guess(adj, node)) ? 1 : 0));
      }
    }

    return result;
  }

  int countCorrect(List<Integer>[] adjLists, Set<Guess> guessSet, int parent, int node) {
    int result = guessSet.contains(new Guess(parent, node)) ? 1 : 0;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        result += countCorrect(adjLists, guessSet, node, adj);
      }
    }

    return result;
  }
}

record Guess(int parent, int child) {}
