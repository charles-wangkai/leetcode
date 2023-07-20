import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<String> findAllRecipes(
      String[] recipes, List<List<String>> ingredients, String[] supplies) {
    Map<String, Set<String>> recipeToNeeded =
        IntStream.range(0, recipes.length)
            .boxed()
            .collect(Collectors.toMap(i -> recipes[i], i -> new HashSet<>(ingredients.get(i))));

    Queue<String> completed = new ArrayDeque<>();
    for (String supply : supplies) {
      completed.offer(supply);
    }

    while (!completed.isEmpty()) {
      String s = completed.poll();
      for (String recipe : recipeToNeeded.keySet()) {
        Set<String> needed = recipeToNeeded.get(recipe);
        if (needed.contains(s)) {
          needed.remove(s);
          if (needed.isEmpty()) {
            completed.offer(recipe);
          }
        }
      }
    }

    return recipeToNeeded.keySet().stream()
        .filter(recipe -> recipeToNeeded.get(recipe).isEmpty())
        .toList();
  }
}
