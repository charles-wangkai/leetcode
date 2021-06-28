import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MovieRentingSystem {
  static final int MAX_RESULT_SIZE = 5;

  int[][] entries;
  Map<Element, Integer> elementToIndex;
  Comparator<Integer> comparator =
      Comparator.comparing((Integer i) -> entries[i][2])
          .thenComparing(i -> entries[i][0])
          .thenComparing(i -> entries[i][1]);
  Map<Integer, NavigableSet<Integer>> movieToUnrented = new HashMap<>();
  NavigableSet<Integer> rented = new TreeSet<>(comparator);

  public MovieRentingSystem(int n, int[][] entries) {
    this.entries = entries;

    elementToIndex =
        IntStream.range(0, entries.length)
            .boxed()
            .collect(Collectors.toMap(i -> new Element(entries[i][0], entries[i][1]), i -> i));

    for (int i = 0; i < entries.length; ++i) {
      int movie = entries[i][1];

      if (!movieToUnrented.containsKey(movie)) {
        movieToUnrented.put(movie, new TreeSet<>(comparator));
      }
      movieToUnrented.get(movie).add(i);
    }
  }

  public List<Integer> search(int movie) {
    if (!movieToUnrented.containsKey(movie)) {
      return List.of();
    }

    List<Integer> result = new ArrayList<>();
    for (int i : movieToUnrented.get(movie)) {
      result.add(entries[i][0]);

      if (result.size() == MAX_RESULT_SIZE) {
        break;
      }
    }

    return result;
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
    List<List<Integer>> result = new ArrayList<>();
    for (int i : rented) {
      result.add(List.of(entries[i][0], entries[i][1]));

      if (result.size() == MAX_RESULT_SIZE) {
        break;
      }
    }

    return result;
  }
}

class Element {
  int shop;
  int movie;

  Element(int shop, int movie) {
    this.shop = shop;
    this.movie = movie;
  }

  @Override
  public int hashCode() {
    return Objects.hash(shop, movie);
  }

  @Override
  public boolean equals(Object obj) {
    Element other = (Element) obj;

    return shop == other.shop && movie == other.movie;
  }
}

 // Your MovieRentingSystem object will be instantiated and called as such:
 // MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 // List<Integer> param_1 = obj.search(movie);
 // obj.rent(shop,movie);
 // obj.drop(shop,movie);
 // List<List<Integer>> param_4 = obj.report();
