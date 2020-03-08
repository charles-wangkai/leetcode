import java.util.ArrayList;
import java.util.List;

class Solution {
    double targetProb;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        if (target == 1) {
            return (n == 1) ? 1 : 0;
        }

        @SuppressWarnings("unchecked")
        List<Integer>[] adjLists = new List[n + 1];
        for (int i = 1; i < adjLists.length; ++i) {
            adjLists[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adjLists[edge[0]].add(edge[1]);
            adjLists[edge[1]].add(edge[0]);
        }

        search(adjLists, t, target, new boolean[n + 1], 0, 1, 1);

        return targetProb;
    }

    void search(List<Integer>[] adjLists, int t, int target, boolean[] visited, int step, double prob, int vertex) {
        visited[vertex] = true;

        if (vertex == target) {
            targetProb = (step == t || (step < t && adjLists[vertex].size() == 1)) ? prob : 0;

            return;
        }

        for (int adj : adjLists[vertex]) {
            if (!visited[adj]) {
                search(adjLists, t, target, visited, step + 1, prob / (adjLists[vertex].size() - (vertex == 1 ? 0 : 1)),
                        adj);
            }
        }
    }
}