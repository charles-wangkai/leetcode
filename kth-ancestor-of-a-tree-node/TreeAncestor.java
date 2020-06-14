import java.util.ArrayList;
import java.util.List;

class TreeAncestor {
    private List<Integer>[] parentLists;

    @SuppressWarnings("unchecked")
    public TreeAncestor(int n, int[] parent) {
        parentLists = new List[n];
        for (int i = 0; i < parentLists.length; ++i) {
            parentLists[i] = new ArrayList<>();
        }

        List<Integer>[] childLists = new List[n];
        for (int i = 0; i < childLists.length; ++i) {
            childLists[i] = new ArrayList<>();
        }
        for (int i = 0; i < parent.length; ++i) {
            if (parent[i] != -1) {
                childLists[parent[i]].add(i);
            }
        }

        search(childLists, new ArrayList<>(), 0);
    }

    private void search(List<Integer>[] childLists, List<Integer> path, int node) {
        for (int i = 1; path.size() >= i; i *= 2) {
            parentLists[node].add(path.get(path.size() - i));
        }

        path.add(node);

        for (int child : childLists[node]) {
            search(childLists, path, child);
        }

        path.remove(path.size() - 1);
    }

    public int getKthAncestor(int node, int k) {
        if (k == 0) {
            return node;
        }
        if (parentLists[node].isEmpty()) {
            return -1;
        }

        for (int i = parentLists[node].size() - 1;; --i) {
            if (k >= 1 << i) {
                return getKthAncestor(parentLists[node].get(i), k - (1 << i));
            }
        }
    }
}

// Your TreeAncestor object will be instantiated and called as such:
// TreeAncestor obj = new TreeAncestor(n, parent);
// int param_1 = obj.getKthAncestor(node,k);