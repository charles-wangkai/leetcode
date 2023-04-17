import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Graph {
  int n;
  List<int[]> edges;

  public Graph(int n, int[][] edges) {
    this.n = n;
    this.edges = new ArrayList<>(Arrays.asList(edges));
  }

  public void addEdge(int[] edge) {
    edges.add(edge);
  }

  public int shortestPath(int node1, int node2) {
    @SuppressWarnings("unchecked")
    List<int[]>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      edgeLists[edge[0]].add(edge);
    }

    int[] distances = new int[n];
    Arrays.fill(distances, -1);
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(node1, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node()] == -1) {
        distances[head.node()] = head.distance();

        for (int[] edge : edgeLists[head.node()]) {
          if (distances[edge[1]] == -1) {
            pq.offer(new Element(edge[1], head.distance() + edge[2]));
          }
        }
      }
    }

    return distances[node2];
  }
}

record Element(int node, int distance) {}

// Your Graph object will be instantiated and called as such:
// Graph obj = new Graph(n, edges);
// obj.addEdge(edge);
// int param_2 = obj.shortestPath(node1,node2);
