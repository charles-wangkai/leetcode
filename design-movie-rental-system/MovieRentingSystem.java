import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MovieRentingSystem {
  int[][] entries;
  Map<Element, Integer> elementToIndex;
  Map<Integer, NavigableSet<Integer>> movieToUnrented = new HashMap<>();
  NavigableSet<Integer> rented;

  public MovieRentingSystem(int n, int[][] entries) {
    this.entries = entries;

    elementToIndex =
        IntStream.range(0, entries.length)
            .boxed()
            .collect(Collectors.toMap(i -> new Element(entries[i][0], entries[i][1]), i -> i));

    Comparator<Integer> comparator =
        Comparator.<Integer, Integer>comparing(i -> entries[i][2])
            .thenComparing(i -> entries[i][0])
            .thenComparing(i -> entries[i][1]);

    for (int i = 0; i < entries.length; ++i) {
      int movie = entries[i][1];

      movieToUnrented.putIfAbsent(movie, new TreeSet<>(comparator));
      movieToUnrented.get(movie).add(i);
    }

    rented = new TreeSet<>(comparator);
  }

  public List<Integer> search(int movie) {
    return movieToUnrented.containsKey(movie)
        ? movieToUnrented.get(movie).stream().map(index -> entries[index][0]).limit(5).toList()
        : List.of();
  }

  public void rent(int shop, int movie) {
    int index = elementToIndex.get(new Element(shop, movie));

    movieToUnrented.get(movie).remove(index);
    rented.add(index);
  }

  public void drop(int shop, int movie) {
    int index = elementToIndex.get(new Element(shop, movie));

    movieToUnrented.get(movie).add(index);
    rented.remove(index);
  }

  public List<List<Integer>> report() {
    return rented.stream()
        .map(index -> List.of(entries[index][0], entries[index][1]))
        .limit(5)
        .toList();
  }
}

record Element(int shop, int movie) {}

 // Your MovieRentingSystem object will be instantiated and called as such:
 // MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 // List<Integer> param_1 = obj.search(movie);
 // obj.rent(shop,movie);
 // obj.drop(shop,movie);
 // List<List<Integer>> param_4 = obj.report();
