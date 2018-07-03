import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Definition for undirected graph.
class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
}

public class Solution {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}
		Map<UndirectedGraphNode, UndirectedGraphNode> cloneMap = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		Set<UndirectedGraphNode> visitedNodes = new HashSet<UndirectedGraphNode>();
		clone(cloneMap, visitedNodes, node);
		return cloneMap.get(node);
	}

	void clone(Map<UndirectedGraphNode, UndirectedGraphNode> cloneMap,
			Set<UndirectedGraphNode> visitedNodes, UndirectedGraphNode node) {
		visitedNodes.add(node);
		UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
		cloneMap.put(node, cloned);
		for (UndirectedGraphNode neighbor : node.neighbors) {
			if (!visitedNodes.contains(neighbor)) {
				clone(cloneMap, visitedNodes, neighbor);
			}
			cloned.neighbors.add(cloneMap.get(neighbor));
		}
	}
}
