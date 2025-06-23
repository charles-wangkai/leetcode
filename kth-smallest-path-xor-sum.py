# https://leetcode.com/problems/kth-smallest-path-xor-sum/solutions/6873587/python3-small-to-large-merging-with-proof-o-n-log-2-n/

from sortedcontainers import SortedSet


def merge_sets(set1, set2):
    if len(set1) < len(set2):
        set2, set1 = set1, set2

    set1 |= set2

    return set1


class Solution:
    def kthSmallest(
        self, par: List[int], vals: List[int], queries: List[List[int]]
    ) -> List[int]:
        def search(node, parent_xor):
            current_xor = parent_xor ^ vals[node]

            merged_set = SortedSet()
            merged_set.add(current_xor)

            for child in node_to_children[node]:
                child_set = search(child, current_xor)
                merged_set = merge_sets(merged_set, child_set)

            for query_index in node_to_query_indices[node]:
                if queries[query_index][1] <= len(merged_set):
                    result[query_index] = merged_set[queries[query_index][1] - 1]

            return merged_set

        node_to_query_indices = defaultdict(list)
        for i in range(len(queries)):
            node_to_query_indices[queries[i][0]].append(i)

        node_to_children = defaultdict(list)
        for i in range(len(par)):
            node_to_children[par[i]].append(i)

        result = [-1] * len(queries)
        search(0, 0)

        return result
