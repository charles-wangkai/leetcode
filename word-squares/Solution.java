import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<String>> wordSquares(String[] words) {
    int size = words[0].length();

    Map<String, List<String>> prefixToWords = new HashMap<>();
    for (String word : words) {
      for (int i = 0; i < word.length(); ++i) {
        String prefix = word.substring(0, i);
        if (!prefixToWords.containsKey(prefix)) {
          prefixToWords.put(prefix, new ArrayList<>());
        }
        prefixToWords.get(prefix).add(word);
      }
    }

    List<List<String>> squares = new ArrayList<>();
    search(squares, prefixToWords, size, new ArrayList<>());

    return squares;
  }

  void search(
      List<List<String>> squares,
      Map<String, List<String>> prefixToWords,
      int size,
      List<String> square) {
    int index = square.size();

    if (index == size) {
      squares.add(new ArrayList<>(square));

      return;
    }

    String prefix =
        IntStream.range(0, index)
            .mapToObj(i -> String.valueOf(square.get(i).charAt(index)))
            .collect(Collectors.joining());

    if (prefixToWords.containsKey(prefix)) {
      for (String word : prefixToWords.get(prefix)) {
        square.add(word);

        search(squares, prefixToWords, size, square);

        square.remove(square.size() - 1);
      }
    }
  }
}
