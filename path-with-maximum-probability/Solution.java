import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    static final double EPSILON = 1e-9;

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        @SuppressWarnings("unchecked")
        List<Integer>[] edgeLists = new List[n];
        for (int i = 0; i < edgeLists.length; ++i) {
            edgeLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; ++i) {
            edgeLists[edges[i][0]].add(i);
            edgeLists[edges[i][1]].add(i);
        }

        double[] probs = new double[n];
        Arrays.fill(probs, -Double.MAX_VALUE);

        PriorityQueue<Element> pq = new PriorityQueue<>((e1, e2) -> -Double.compare(e1.prob, e2.prob));
        pq.offer(new Element(start, 1));

        while (!pq.isEmpty()) {
            Element head = pq.poll();

            if (probs[head.node] > -EPSILON) {
                continue;
            }
            if (head.node == end) {
                return head.prob;
            }

            probs[head.node] = head.prob;

            for (int edge : edgeLists[head.node]) {
                int other = (head.node == edges[edge][0]) ? edges[edge][1] : edges[edge][0];

                if (probs[other] < -EPSILON) {
                    pq.offer(new Element(other, head.prob * succProb[edge]));
                }
            }
        }

        return 0;
    }
}

class Element {
    int node;
    double prob;

    Element(int node, double prob) {
        this.node = node;
        this.prob = prob;
    }
}