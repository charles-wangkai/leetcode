import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
  public int numBusesToDestination(int[][] routes, int source, int target) {
    Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
    for (int i = 0; i < routes.length; ++i) {
      for (int stop : routes[i]) {
        stopToRoutes.putIfAbsent(stop, new ArrayList<>());
        stopToRoutes.get(stop).add(i);
      }
    }

    Map<Integer, Integer> stopToDistance = new HashMap<>();
    stopToDistance.put(source, 0);
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(source);
    Set<Integer> usedRoutes = new HashSet<>();
    while (!queue.isEmpty()) {
      int stop = queue.poll();

      for (int route : stopToRoutes.get(stop)) {
        if (!usedRoutes.contains(route)) {
          usedRoutes.add(route);

          for (int adjStop : routes[route]) {
            if (!stopToDistance.containsKey(adjStop)) {
              stopToDistance.put(adjStop, stopToDistance.get(stop) + 1);
              queue.offer(adjStop);
            }
          }
        }
      }
    }

    return stopToDistance.getOrDefault(target, -1);
  }
}
