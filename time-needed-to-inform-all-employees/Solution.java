import java.util.ArrayList;
import java.util.List;

class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        @SuppressWarnings("unchecked")
        List<Integer>[] childrens = new List[n];
        for (int i = 0; i < childrens.length; ++i) {
            childrens[i] = new ArrayList<>();
        }

        for (int i = 0; i < manager.length; ++i) {
            if (manager[i] != -1) {
                childrens[manager[i]].add(i);
            }
        }

        return search(childrens, informTime, headID, 0);
    }

    int search(List<Integer>[] childrens, int[] informTime, int node, int time) {
        int result = time;
        for (int child : childrens[node]) {
            result = Math.max(result, search(childrens, informTime, child, time + informTime[node]));
        }

        return result;
    }
}