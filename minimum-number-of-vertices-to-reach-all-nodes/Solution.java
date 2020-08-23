import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] incomes = new boolean[n];
        for (List<Integer> edge : edges) {
            incomes[edge.get(1)] = true;
        }

        return IntStream.range(0, incomes.length).filter(i -> !incomes[i]).boxed().collect(Collectors.toList());
    }
}