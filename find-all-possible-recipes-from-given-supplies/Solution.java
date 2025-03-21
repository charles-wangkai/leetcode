import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
  public List<String> findAllRecipes(
      String[] recipes, List<List<String>> ingredients, String[] supplies) {
    Map<String, Set<String>> recipeToNeeded = new HashMap<>();
    Map<String, List<String>> neededToRecipes = new HashMap<>();
    for (int i = 0; i < recipes.length; ++i) {
      for (String needed : ingredients.get(i)) {
        recipeToNeeded.putIfAbsent(recipes[i], new HashSet<>());
        recipeToNeeded.get(recipes[i]).add(needed);

        neededToRecipes.putIfAbsent(needed, new ArrayList<>());
        neededToRecipes.get(needed).add(recipes[i]);
      }
    }

    Queue<String> completed = new ArrayDeque<>();
    for (String supply : supplies) {
      completed.offer(supply);
    }

    while (!completed.isEmpty()) {
      String s = completed.poll();
      for (String recipe : neededToRecipes.getOrDefault(s, List.of())) {
        recipeToNeeded.get(recipe).remove(s);
        if (recipeToNeeded.get(recipe).isEmpty()) {
          completed.offer(recipe);
        }
      }
    }

    return recipeToNeeded.keySet().stream()
        .filter(recipe -> recipeToNeeded.get(recipe).isEmpty())
        .toList();
  }
}
