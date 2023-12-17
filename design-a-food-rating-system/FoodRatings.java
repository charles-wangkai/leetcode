import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FoodRatings {
  String[] foods;
  String[] cuisines;
  int[] ratings;
  Map<String, Integer> foodToIndex;
  Map<String, SortedSet<Integer>> cuisineToSortedIndices = new HashMap<>();

  public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
    this.foods = foods;
    this.cuisines = cuisines;
    this.ratings = ratings;

    foodToIndex =
        IntStream.range(0, foods.length).boxed().collect(Collectors.toMap(i -> foods[i], i -> i));

    for (int i = 0; i < cuisines.length; ++i) {
      cuisineToSortedIndices.putIfAbsent(
          cuisines[i],
          new TreeSet<>(
              Comparator.<Integer, Integer>comparing(j -> ratings[j])
                  .reversed()
                  .thenComparing(j -> foods[j])));
      cuisineToSortedIndices.get(cuisines[i]).add(i);
    }
  }

  public void changeRating(String food, int newRating) {
    int index = foodToIndex.get(food);

    cuisineToSortedIndices.get(cuisines[index]).remove(index);
    ratings[index] = newRating;
    cuisineToSortedIndices.get(cuisines[index]).add(index);
  }

  public String highestRated(String cuisine) {
    return foods[cuisineToSortedIndices.get(cuisine).first()];
  }
}

// Your FoodRatings object will be instantiated and called as such:
// FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
// obj.changeRating(food,newRating);
// String param_2 = obj.highestRated(cuisine);
