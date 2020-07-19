import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        @SuppressWarnings("unchecked")
        List<Integer>[] adjLists = new List[n];
        for (int i = 0; i < adjLists.length; ++i) {
            adjLists[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adjLists[edge[0]].add(edge[1]);
            adjLists[edge[1]].add(edge[0]);
        }

        int[] sameLabelNums = new int[n];
        search(sameLabelNums, labels, adjLists, new boolean[n], 0);

        return sameLabelNums;
    }

    Map<Character, Integer> search(int[] sameLabelNums, String labels, List<Integer>[] adjLists, boolean[] visited,
            int node) {
        visited[node] = true;

        char label = labels.charAt(node);

        Map<Character, Integer> labelToCount = new HashMap<>();
        labelToCount.put(label, 1);

        for (int adj : adjLists[node]) {
            if (!visited[adj]) {
                Map<Character, Integer> subResult = search(sameLabelNums, labels, adjLists, visited, adj);
                for (char l : subResult.keySet()) {
                    labelToCount.put(l, labelToCount.getOrDefault(l, 0) + subResult.get(l));
                }
            }
        }
        sameLabelNums[node] = labelToCount.get(label);

        return labelToCount;
    }
}