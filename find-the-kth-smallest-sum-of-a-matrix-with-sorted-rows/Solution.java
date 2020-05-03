import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        List<Integer> initialIndices = IntStream.range(0, m).mapToObj(i -> 0).collect(Collectors.toList());

        PriorityQueue<Element> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.sum, e2.sum));
        pq.offer(new Element(initialIndices, IntStream.range(0, m).map(i -> mat[i][0]).sum()));

        Set<List<Integer>> seen = new HashSet<>();
        seen.add(initialIndices);

        for (int i = 0; i < k - 1; ++i) {
            Element head = pq.poll();

            for (int j = 0; j < m; ++j) {
                if (head.indices.get(j) != n - 1) {
                    List<Integer> nextIndices = new ArrayList<>(head.indices);
                    nextIndices.set(j, head.indices.get(j) + 1);

                    if (!seen.contains(nextIndices)) {
                        int nextSum = head.sum - mat[j][head.indices.get(j)] + mat[j][head.indices.get(j) + 1];

                        pq.offer(new Element(nextIndices, nextSum));
                        seen.add(nextIndices);
                    }
                }
            }
        }

        return pq.peek().sum;
    }
}

class Element {
    List<Integer> indices;
    int sum;

    Element(List<Integer> indices, int sum) {
        this.indices = indices;
        this.sum = sum;
    }
}