import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class DistanceLimitedPathsExist {
  List<Element>[] parentLists;

  @SuppressWarnings("unchecked")
  public DistanceLimitedPathsExist(int n, int[][] edgeList) {
    parentLists = new List[n];
    for (int i = 0; i < parentLists.length; ++i) {
      parentLists[i] = new ArrayList<>();
    }

    int[] parents = new int[n];
    Arrays.fill(parents, -1);
    Arrays.sort(edgeList, Comparator.comparing(edge -> edge[2]));
    for (int[] edge : edgeList) {
      int root1 = findRoot1(parents, edge[0]);
      int root2 = findRoot1(parents, edge[1]);
      if (root1 != root2) {
        parents[root2] = root1;
        parentLists[root2].add(new Element(edge[2], root1));
      }
    }
  }

  public boolean query(int p, int q, int limit) {
    return findRoot2(p, limit) == findRoot2(q, limit);
  }

  int findRoot1(int[] parents, int node) {
    int root = node;
    while (parents[root] != -1) {
      root = parents[root];
    }

    int p = node;
    while (p != root) {
      int next = parents[p];
      parents[p] = root;

      p = next;
    }

    return root;
  }

  int findRoot2(int node, int limit) {
    int root = node;
    while (true) {
      int parent = findParent(root, limit);
      if (parent == -1) {
        break;
      }

      root = parent;
    }

    return root;
  }

  int findParent(int node, int limit) {
    int parent = -1;
    for (Element element : parentLists[node]) {
      if (element.distance < limit) {
        parent = element.parent;
      }
    }

    return parent;
  }
}

class Element {
  int distance;
  int parent;

  Element(int distance, int parent) {
    this.distance = distance;
    this.parent = parent;
  }
}

// Your DistanceLimitedPathsExist object will be instantiated and called as such:
// DistanceLimitedPathsExist obj = new DistanceLimitedPathsExist(n, edgeList);
// boolean param_1 = obj.query(p,q,limit);
